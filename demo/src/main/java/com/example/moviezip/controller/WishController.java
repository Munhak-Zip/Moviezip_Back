package com.example.moviezip.controller;

import com.example.moviezip.domain.CustomUserDetails;
import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.Review;
import com.example.moviezip.service.WishImpl;
import org.apache.hadoop.yarn.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wish")
public class WishController {
    private final WishImpl wishService;

    @Autowired
    public WishController(WishImpl wishService) {
        this.wishService = wishService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/movies")
    public List<Movie> getWishMovies(@AuthenticationPrincipal CustomUserDetails user) {
        List<Movie> movieList = wishService.getWishMovie(user.getUser());
        for(Movie movie :movieList){
            System.out.println("찜한영화 : "+movie.getMvTitle());
        }

        return wishService.getWishMovie(user.getUser());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/reviews")
    public List<Review> getWishReviews(@AuthenticationPrincipal CustomUserDetails user) {
        List<Review> reviewList = wishService.getWishReview(user.getUser());
        for(Review review : reviewList){
            System.out.println("찜한리뷰 : "+review.getMvTitle() + "\n" + review.getWriter() + "\n" + review.getContent());
        }
        return wishService.getWishReview(user.getUser());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/myreviews")
    public List<Review> getMyReviews(@AuthenticationPrincipal CustomUserDetails user) {
        List<Review> reviewList = wishService.getMyReview(user.getUsername());
        for(Review review : reviewList){
            System.out.println("나의리뷰 : "+review.getMvTitle() + "\n" + review.getContent());
        }

        return wishService.getMyReview(user.getUsername());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{rvId}")
    public void deleteWishReview(@AuthenticationPrincipal CustomUserDetails user, @PathVariable int rvId) {
        int result = wishService.deleteWishReview(user.getUser(), rvId);
        if (result <= 0) {
            throw new ResourceNotFoundException("Review not found with id " + rvId);
        }
    }
}
