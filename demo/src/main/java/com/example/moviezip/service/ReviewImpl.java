package com.example.moviezip.service;

import com.example.moviezip.dao.mybatis.MybatisReviewDao;
import com.example.moviezip.domain.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewImpl {
    private final MybatisReviewDao mybatisReviewDao;

    public ReviewImpl(MybatisReviewDao mybatisReviewDao) {
        this.mybatisReviewDao = mybatisReviewDao;
    }

    public void insertMyReview(Review review) {
        mybatisReviewDao.insertMyReview(review);
    }

    public List<Review> getMyReviewList(String userId) { return mybatisReviewDao.getMyReviewList(userId); }
    public void deleteReview(int rvId){
        mybatisReviewDao.deleteReview(rvId);
    }

    public List<Review> getReviewList(long mvId) {
        return mybatisReviewDao.getReviewList(mvId);
    }

    public Review getReviewDetail(int rvId) {
        return mybatisReviewDao.getReviewDetail(rvId);
    }
    public int updateReview(Review review) { return mybatisReviewDao.updateReview(review); }

    public List<Review> getCriticReviews(int mvId) {
        return mybatisReviewDao.getCriticReviews(mvId);
    }

}
