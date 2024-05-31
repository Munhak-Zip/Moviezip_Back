package com.example.moviezip.controller;

import com.example.moviezip.service.recommend.MovieRecommenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class MovieRecommendController {
    private MovieRecommenderService recommenderService;
    @Autowired
    public void setMovieRecommend(MovieRecommenderService recommenderService) {
        this.recommenderService = recommenderService;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/main/recommend")
    public List<String> recommendMovies(@RequestParam int userId) throws Exception {
        // MovieRecommender 실행
        List<String> recommendations = recommenderService.recommendMovies(userId); // 수정된 부분
        if (recommendations.isEmpty()) {
            System.out.println("없다");
        }
        for (String str : recommendations) {
            System.out.println("값" + str);
        }
        return recommendations;
    }
}