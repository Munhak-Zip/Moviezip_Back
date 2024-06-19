package com.example.moviezip.controller;

import com.example.moviezip.domain.CustomUserDetails;
import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.Review;
import com.example.moviezip.service.WishImpl;
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
    public ResponseEntity<List<Movie>> getWishMovies(@AuthenticationPrincipal CustomUserDetails user) {
        List<Movie> movies = wishService.getWishMovie(user.getUser());
        return ResponseEntity.ok(movies);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getWishReviews(@AuthenticationPrincipal CustomUserDetails user) {
        List<Review> reviews = wishService.getWishReview(user.getUser());
        return ResponseEntity.ok(reviews);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/myreviews")
    public ResponseEntity<List<Review>> getMyReviews(@AuthenticationPrincipal CustomUserDetails user) {
        List<Review> reviews = wishService.getMyReview(user.getUsername());
        return ResponseEntity.ok(reviews);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{rvId}")
    public ResponseEntity<Void> deleteWishReview(@AuthenticationPrincipal CustomUserDetails user, @PathVariable int rvId) {
        int result = wishService.deleteWishReview(user.getUser(), rvId);
        if (result > 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
