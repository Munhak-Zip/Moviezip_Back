package com.example.moviezip.controller;

import com.example.moviezip.domain.Movie;
import com.example.moviezip.service.MovieImpl;
import com.example.moviezip.service.recommend.MovieRecommenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/")
public class MovieRecommendController {
    private MovieRecommenderService recommenderService;
    private MovieImpl movieService;
    private List<String> recommendedMovies; // 추천 영화 저장 변수
    private long lastRecommendationTime; // 마지막 추천 시간 저장 변수
    @Autowired
    public void setMovieRecommend(MovieRecommenderService recommenderService, MovieImpl movieService) {
        this.recommenderService = recommenderService;
        this.movieService = movieService;
        this.recommendedMovies = new ArrayList<>();
        this.lastRecommendationTime = 0;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/main/recommend")
    public List<Movie> recommendMovies(@RequestParam int userId) throws Exception {
        long currentTime = System.currentTimeMillis();
        // 하루가 지났는지 확인 (24시간 = 86400000 milliseconds)
        if (currentTime - lastRecommendationTime > 86400000) {
            // MovieRecommender 실행
            recommendedMovies = recommenderService.recommendMovies(userId);
            lastRecommendationTime = currentTime; // 마지막 추천 시간 업데이트
        }

        if (recommendedMovies.isEmpty()) {
            System.out.println("추천 영화가 없습니다.");
            return new ArrayList<>(); // 비어 있는 경우 빈 리스트 반환
        }
        // MovieRecommender 실행
        //List<String> recommendations = recommenderService.recommendMovies(userId); // 수정된 부분
        //if (recommendations.isEmpty()) {
        //    System.out.println("없다");
        //}
        List<Movie> movieList = new ArrayList<>();
        for (String str : recommendedMovies) {
            System.out.println("값" + str);

            String[] parts = str.split(", ");
            String title = parts[0].split(": ")[1].replace("\"", "");
            System.out.println(title);

            Movie movie = movieService.getMovieTitle(title);
            if (movie != null) {
                movieList.add(movie);
            } else {
                System.out.println("영화 정보를 찾을 수 없습니다: " + title);
            }
        }

        for(Movie s : movieList){
            System.out.println("영화명: " + s.getMvTitle() + " 별점: " + s.getMvStar());
        }

        //return값을 movieList로 바꾸면 됨  retrurn movieList
        //return recommendations;
        return movieList;
    }
}