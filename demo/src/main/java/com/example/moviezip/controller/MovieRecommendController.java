package com.example.moviezip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MovieRecommendController {

    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }
}
