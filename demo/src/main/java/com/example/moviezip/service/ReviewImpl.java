package com.example.moviezip.service;

import com.example.moviezip.dao.mybatis.MybatisReviewDao;
import com.example.moviezip.domain.Review;
import org.springframework.stereotype.Service;

@Service
public class ReviewImpl {
    private final MybatisReviewDao mybatisReviewDao;

    public ReviewImpl(MybatisReviewDao mybatisReviewDao) {
        this.mybatisReviewDao = mybatisReviewDao;
    }

    public void insertMyReview(Review review) {
        mybatisReviewDao.insertMyReview(review);
    }
}
