package com.example.moviezip.controller;

import com.example.moviezip.domain.Movie;
import com.example.moviezip.service.MovieImpl;
import com.example.moviezip.service.recommend.MovieRecommenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/")
public class MovieController {
    private MovieImpl movieService;

    @Autowired
    public void setMovieService(MovieImpl movieService) {
        this.movieService = movieService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("movie/{mvId}")
    public Movie getMovie(@PathVariable int mvId) throws Exception {
        System.out.println("Entering getMovie method with mvId: " + mvId);
        Movie mv = movieService.getAllMoviedetail(mvId);
        if (mv != null) {
            System.out.println("영화제목222: " + mv.getMvTitle());
            System.out.println("영화제목: " + mv.getMvStar());
            System.out.println(mv.getMvDetail());
            System.out.println(mv.getMvId());
            System.out.println(mv.getOpenDate());
            System.out.println(mv.getMvDirector());
            System.out.println(mv.getGenre2());
        } else {
            System.out.println("영화 정보를 찾을 수 없습니다.");
        }
        return mv;
    }
}