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

        public static void main(String[] args) {
            SparkSession spark = SparkSession.builder()
                    .appName("MovieRecommenderWithDirectors")
                    .config("spark.master", "local")
                    .getOrCreate();

            JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());

            JavaRDD<String> data = jsc.textFile("./demo/src/main/resources/csv/rating.csv");

            JavaRDD<String> filteredData = data.filter(line -> !line.startsWith("MOVIE_TITLE"));

            // 영화 ID, 제목, 장르, 감독을 매핑하는 RDD 생성
            JavaPairRDD<Integer, Tuple3<String, String, String>> movieTitlesGenresDirectors = filteredData.mapToPair(line -> {
                String[] parts = line.split(",");
                int movieId = parts[0].hashCode();
                String movieTitle = parts[0];
                String genre = parts[3];
                String director = parts[2]; // 감독 정보 포함
                return new Tuple2<>(movieId, new Tuple3<>(movieTitle, genre, director));
            });

            JavaRDD<Rating> ratings = filteredData.map(line -> {
                try {
                    String[] parts = line.split(",");
                    int userId = Integer.parseInt(parts[4]);
                    int movieId = parts[0].hashCode();
                    double rating = Double.parseDouble(parts[1]);
                    return new Rating(userId, movieId, rating);
                } catch (Exception e) {
                    return null;
                }
            }).filter(Objects::nonNull);

            // movieTitlesGenresDirectors RDD를 Map으로 변환
            Map<Integer, Tuple3<String, String, String>> movieInfoMap = movieTitlesGenresDirectors.collectAsMap();

            // 사용자별로 본 영화의 감독 목록 추출
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

            // 사용자별로 본 영화 목록 추출
            JavaPairRDD<Integer, Iterable<Integer>> userWatchedMovies = ratings.mapToPair(rating -> new Tuple2<>(rating.user(), rating.product())).groupByKey();

            // 데이터를 훈련 데이터와 테스트 데이터로 분할
            JavaRDD<Rating> trainingData = ratings.randomSplit(new double[]{0.8, 0.2})[0];

            // ALS 모델 학습
            int rank = 10;
            int numIterations = 10;
            double lambda = 0.01;
            MatrixFactorizationModel model = ALS.train(JavaRDD.toRDD(trainingData), rank, numIterations, lambda);

            int userId = 3;
            List<Integer> watchedMovies = new ArrayList<>();
            if (userWatchedMovies.lookup(userId).size() > 0) {
                watchedMovies.addAll((Collection<? extends Integer>) userWatchedMovies.lookup(userId).iterator().next());
            }
            Set<String> watchedDirectors = userWatchedDirectors.lookup(userId).iterator().next();

            // 모든 영화 ID 목록 생성
            Set<Integer> allMovieIds = new HashSet<>(movieTitlesGenresDirectors.keys().collect());

            // 사용자가 보지 않은 영화 ID 목록 생성
            List<Integer> notWatchedMovies = new ArrayList<>(allMovieIds);
            notWatchedMovies.removeAll(watchedMovies);

            // 사용자가 보지 않은 영화에 대해 추천 실행
            JavaRDD<Integer> notWatchedMoviesRDD = jsc.parallelize(notWatchedMovies);
            JavaRDD<Tuple2<Object, Object>> notWatchedMoviesUser = notWatchedMoviesRDD.map(movieId -> new Tuple2<>(userId, movieId));
            JavaPairRDD<Tuple2<Integer, Integer>, Double> recommendations = JavaPairRDD.fromJavaRDD(
                    model.predict(JavaRDD.toRDD(notWatchedMoviesUser)).toJavaRDD().map(
                            r -> {
                                // 사용자가 본 영화 감독과 동일한 감독의 영화에 가중치 추가
                                Tuple3<String, String, String> movieInfo = movieInfoMap.get(r.product());
                                double weight = watchedDirectors.contains(movieInfo._3()) ? 1.2 : 1.0; // 가중치 설정
                                return new Tuple2<>(new Tuple2<>(r.user(), r.product()), r.rating() * weight);
                            }
                    ));

            // 추천 영화 결과를 담을 리스트 생성
            List<String> recommendationResults = new ArrayList<>();
            // 추천 결과 정렬 및 상위 8개만 선택
            List<Tuple2<Tuple2<Integer, Integer>, Double>> sortedRecommendations = recommendations.collect()
                    .stream()
                    .sorted((r1, r2) -> r2._2.compareTo(r1._2)) // 평점에 따라 내림차순 정렬
                    .limit(8) // 상위 8개만 선택
                    .collect(Collectors.toList());

            sortedRecommendations.forEach(recommendation -> {
                Tuple3<String, String, String> movieTitleGenreDirector = movieInfoMap.get(recommendation._1()._2());
                String resultString = "결과 Movie Title: " + movieTitleGenreDirector._1() + ", Genre: " + movieTitleGenreDirector._2() + ", Director: " + movieTitleGenreDirector._3() + ", Rating: " + recommendation._2();
                recommendationResults.add(resultString);
            });
            recommendationResults.forEach(System.out::println);

            jsc.close();
        }

}