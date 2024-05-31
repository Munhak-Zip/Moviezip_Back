package com.example.moviezip.controller;

import com.example.moviezip.service.recommend.MovieRecommenderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieRecommenderController {
    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("/main/recommend")
    public List<String> recommendMovies(@RequestParam int userId) {
        // MovieRecommender 실행
        userId = 3;
        List<String> recommendations = MovieRecommenderService.recommendMovies(userId);
        for( String str : recommendations) {
            System.out.println("값"+str);
        }
        return recommendations;
    }
}
