package com.example.moviezip.controller;

import com.example.moviezip.domain.Review;
import com.example.moviezip.service.ReviewImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie/mv")
public class ReviewController {

    private final ReviewImpl reviewImpl;

    @Autowired
    public ReviewController(ReviewImpl reviewImpl) {
        this.reviewImpl = reviewImpl;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public void insertMyReview(@RequestBody Review review) {
        try {
            reviewImpl.insertMyReview(review);
            System.out.print("리뷰내용"+review.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            // 필요에 따라 예외 처리 로직 추가
        }
    }
}
