package com.example.moviezip.controller;

import com.example.moviezip.domain.Movie;
import com.example.moviezip.service.MovieImpl;
import com.example.moviezip.service.recommend.MovieRecommenderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MovieController {
    private MovieImpl movieService;

}
