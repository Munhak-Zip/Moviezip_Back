package com.example.moviezip.service.recommend;

import lombok.RequiredArgsConstructor;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scala.Tuple2;
import scala.Tuple3;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieRecommenderService {
        public static List<String> recommendMovies(int userId)  {
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
                    int userId2 = Integer.parseInt(parts[4]);
                    int movieId = parts[0].hashCode();
                    double rating = Double.parseDouble(parts[1]);
                    return new Rating(userId2, movieId, rating);
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

            // K-Fold 교차 검증 설정
            int numFolds = 5;
            double[] weights = new double[numFolds];
            Arrays.fill(weights, 1.0 / numFolds);

            JavaRDD<Rating>[] folds = ratings.randomSplit(weights);
            double totalRMSE = 0.0;

            MatrixFactorizationModel model = null;
            for (int i = 0; i < numFolds; i++) {
                // 테스트 데이터와 훈련 데이터 분리
                JavaRDD<Rating> testData = folds[i];
                JavaRDD<Rating> trainingData = jsc.parallelize(new ArrayList<>());
                for (int j = 0; j < numFolds; j++) {
                    if (j != i) {
                        trainingData = trainingData.union(folds[j]);
                    }
                }

                // ALS 모델 학습
                int rank = 20;
                int numIterations = 10;
                double lambda = 0.05;
                model = ALS.train(JavaRDD.toRDD(trainingData), rank, numIterations, lambda);

                // 테스트 데이터로 예측
                JavaRDD<Tuple2<Object, Object>> userProducts = testData.map(r -> new Tuple2<>(r.user(), r.product()));
                JavaPairRDD<Tuple2<Integer, Integer>, Double> predictions = JavaPairRDD.fromJavaRDD(
                        model.predict(JavaRDD.toRDD(userProducts)).toJavaRDD().map(
                                r -> new Tuple2<>(new Tuple2<>(r.user(), r.product()), r.rating())
                        ));

                JavaRDD<Tuple2<Double, Double>> ratesAndPreds = testData.mapToPair(
                        r -> new Tuple2<>(new Tuple2<>(r.user(), r.product()), r.rating())
                ).join(predictions).values();

                // RMSE 계산
                double MSE = ratesAndPreds.mapToDouble(pair -> {
                    double err = pair._1() - pair._2();
                    return err * err;
                }).mean();

                double RMSE = Math.sqrt(MSE);
                System.out.println("Fold " + (i + 1) + " RMSE: " + RMSE);
                totalRMSE += RMSE;
            }

            // 평균 RMSE 출력
            double avgRMSE = totalRMSE / numFolds;
            System.out.println("Average RMSE: " + avgRMSE);

            // 사용자 추천을 위해 마지막 모델을 사용
            //int userId = 3;
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
                String resultString = "영화 명: " + movieTitleGenreDirector._1() + ", 장르: " + movieTitleGenreDirector._2() + ", 감독: " + movieTitleGenreDirector._3() + ", 예상 별점: " + recommendation._2();
                recommendationResults.add(resultString);
            });
            recommendationResults.forEach(System.out::println);

            jsc.close();
            return recommendationResults;
        }

}