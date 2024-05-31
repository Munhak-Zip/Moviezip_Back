package com.example.moviezip.service.recommend;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.List;
import java.util.Map;

public class MovieRecommender {

    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("MovieRecommenderWithGenres")
                .config("spark.master", "local")
                .getOrCreate();

        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());

        JavaRDD<String> data = jsc.textFile("./demo/src/main/resources/csv/recommend_movie.csv");

        JavaRDD<String> filteredData = data.filter(line -> !line.startsWith("MOVIE_TITLE"));

        // 영화 ID, 제목, 장르를 매핑하는 RDD 생성
        JavaPairRDD<Integer, Tuple2<String, String>> movieTitlesAndGenres = filteredData.mapToPair(line -> {
            String[] parts = line.split(",");
            int movieId = parts[0].hashCode();
            String movieTitle = parts[0];
            String genre = parts[3]; // 장르 정보를 포함
            return new Tuple2<>(movieId, new Tuple2<>(movieTitle, genre));
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
        }).filter(rating -> rating != null);
// '로맨스' 장르에 해당하는 영화만 필터링
        JavaPairRDD<Integer, Tuple2<String, String>> romanceMovies = movieTitlesAndGenres.filter(
                entry -> entry._2()._2().equals("로맨스"));

// '로맨스' 장르에 해당하는 영화의 ID를 추출
        List<Integer> romanceMovieIds = romanceMovies.keys().collect();

// '로맨스' 장르에 해당하는 영화에 대한 평점만 필터링
        JavaRDD<Rating> filteredRatings = ratings.filter(
                rating -> romanceMovieIds.contains(rating.product()));
        // 데이터를 훈련 데이터와 테스트 데이터로 분할
        JavaRDD<Rating>[] splits = ratings.randomSplit(new double[]{0.8, 0.2});
        JavaRDD<Rating> trainingData = splits[0];
        JavaRDD<Rating> testData = splits[1];

        // ALS 모델 학습
        int rank = 10;
        int numIterations = 10;
        double lambda = 0.01;
        MatrixFactorizationModel model = ALS.train(JavaRDD.toRDD(trainingData), rank, numIterations, lambda);

        // 테스트 데이터에 대한 예측 수행 및 평가
        JavaRDD<Tuple2<Object, Object>> userProducts = testData.map(r -> new Tuple2<>(r.user(), r.product()));
        JavaPairRDD<Tuple2<Integer, Integer>, Double> predictions = JavaPairRDD.fromJavaRDD(
                model.predict(JavaRDD.toRDD(userProducts)).toJavaRDD().map(
                        r -> new Tuple2<>(new Tuple2<>(r.user(), r.product()), r.rating())
                ));
        JavaRDD<Tuple2<Double, Double>> ratesAndPreds = JavaPairRDD.fromJavaRDD(
                testData.map(r -> new Tuple2<>(new Tuple2<>(r.user(), r.product()), r.rating()))).join(predictions).values();
        double MSE = ratesAndPreds.mapToDouble(pair -> {
            double err = pair._1() - pair._2();
            return err * err;
        }).mean();
        System.out.println("Mean Squared Error = " + MSE);

        // 영화 ID와 제목, 장르 매핑을 Map으로 변환
        Map<Integer, Tuple2<String, String>> movieTitleAndGenreMap = movieTitlesAndGenres.collectAsMap();

        int userId = 2;
        Rating[] recommendations = model.recommendProducts(userId, 8);
        for (Rating recommendation : recommendations) {
            Tuple2<String, String> movieTitleAndGenre = movieTitleAndGenreMap.get(recommendation.product());
            if (movieTitleAndGenre != null) {
                System.out.println("결과 Movie Title: " + movieTitleAndGenre._1() + " Genre: " + movieTitleAndGenre._2() + " Rating: " + recommendation.rating());
            }
        }

        jsc.close();
    }


}