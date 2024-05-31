package com.example.moviezip.service.recommend;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;
import scala.Tuple3;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class MovieRecommender {

    public static List<String> recommendMovies(int userId) {
        SparkSession spark = SparkSession.builder()
                .appName("MovieRecommenderWithDirectors")
                .config("spark.master", "local")
                .getOrCreate();

        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());

        JavaRDD<String> data = jsc.textFile("./demo/src/main/resources/csv/rating.csv");

        JavaRDD<String> filteredData = data.filter(line -> !line.startsWith("MOVIE_TITLE"));

        // 필터링된 데이터 로그 출력
        filteredData.collect().forEach(System.out::println);

        JavaPairRDD<Integer, Tuple3<String, String, String>> movieTitlesGenresDirectors = filteredData.mapToPair(line -> {
            String[] parts = line.split(",");
            int movieId = parts[0].hashCode();
            String movieTitle = parts[0];
            String genre = parts[3];
            String director = parts[2];
            return new Tuple2<>(movieId, new Tuple3<>(movieTitle, genre, director));
        });

        JavaRDD<Rating> ratings = filteredData.map(line -> {
            try {
                String[] parts = line.split(",");
                int movieId = parts[0].hashCode();
                double rating = Double.parseDouble(parts[1]);
                return new Rating(userId, movieId, rating);
            } catch (Exception e) {
                e.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
                return null;
            }
        }).filter(Objects::nonNull);

        // ratings RDD 로그 출력
        ratings.collect().forEach(System.out::println);

        Map<Integer, Tuple3<String, String, String>> movieInfoMap = movieTitlesGenresDirectors.collectAsMap();

        JavaPairRDD<Integer, Set<String>> userWatchedDirectors = ratings.mapToPair(rating -> {
            int movieId = rating.product();
            Tuple3<String, String, String> movieInfo = movieInfoMap.get(movieId);
            String director = movieInfo._3();
            return new Tuple2<>(rating.user(), director);
        }).groupByKey().mapToPair(tuple -> {
            Set<String> directors = new HashSet<>();
            for (String director : tuple._2) {
                directors.add(director);
            }
            return new Tuple2<>(tuple._1, directors);
        });

        JavaPairRDD<Integer, Iterable<Integer>> userWatchedMovies = ratings.mapToPair(rating -> new Tuple2<>(rating.user(), rating.product())).groupByKey();

        JavaRDD<Rating> trainingData = ratings.randomSplit(new double[]{0.8, 0.2})[0];

        int rank = 10;
        int numIterations = 10;
        double lambda = 0.01;
        MatrixFactorizationModel model = ALS.train(JavaRDD.toRDD(trainingData), rank, numIterations, lambda);

        List<Integer> watchedMovies = new ArrayList<>();
        if (userWatchedMovies.lookup(userId).size() > 0) {
            watchedMovies.addAll((Collection<? extends Integer>) userWatchedMovies.lookup(userId).iterator().next());
        }
        Set<String> watchedDirectors = userWatchedDirectors.lookup(userId).iterator().next();

        Set<Integer> allMovieIds = new HashSet<>(movieTitlesGenresDirectors.keys().collect());
        List<Integer> notWatchedMovies = new ArrayList<>(allMovieIds);
        notWatchedMovies.removeAll(watchedMovies);

        JavaRDD<Integer> notWatchedMoviesRDD = jsc.parallelize(notWatchedMovies);
        JavaRDD<Tuple2<Object, Object>> notWatchedMoviesUser = notWatchedMoviesRDD.map(movieId -> new Tuple2<>(userId, movieId));
        JavaPairRDD<Tuple2<Integer, Integer>, Double> recommendations = JavaPairRDD.fromJavaRDD(
                model.predict(JavaRDD.toRDD(notWatchedMoviesUser)).toJavaRDD().map(
                        r -> {
                            Tuple3<String, String, String> movieInfo = movieInfoMap.get(r.product());
                            double weight = watchedDirectors.contains(movieInfo._3()) ? 1.2 : 1.0;
                            return new Tuple2<>(new Tuple2<>(r.user(), r.product()), r.rating() * weight);
                        }
                ));

        List<String> recommendationResults = new ArrayList<>();
        List<Tuple2<Tuple2<Integer, Integer>, Double>> sortedRecommendations = recommendations.collect()
                .stream()
                .sorted((r1, r2) -> r2._2.compareTo(r1._2))
                .limit(8)
                .collect(Collectors.toList());

        sortedRecommendations.forEach(recommendation -> {
            Tuple3<String, String, String> movieTitleGenreDirector = movieInfoMap.get(recommendation._1()._2());
            String resultString = "결과 Movie Title: " + movieTitleGenreDirector._1() + ", Genre: " + movieTitleGenreDirector._2() + ", Director: " + movieTitleGenreDirector._3() + ", Rating: " + recommendation._2();
            recommendationResults.add(resultString);
        });

        // 최종 추천 결과 로그 출력
        recommendationResults.forEach(System.out::println);

        jsc.close();
        return recommendationResults;
    }


}