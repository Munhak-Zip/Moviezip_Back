package com.example.moviezip.dao;

import com.example.moviezip.domain.Review;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ReviewDao {

    void insertMyReview(Review review) throws DataAccessException;

    List<Review> getMyReviewList(String userId) throws DataAccessException;

    void deleteReview(int rvId) throws DataAccessException;

    List<Review> getReviewList(long mvId) throws DataAccessException;

    Review getReviewDetail(int rvId) throws DataAccessException;

    int updateReview(Review review) throws DataAccessException;

    List<Review> getCriticReviews(long mvId) throws DataAccessException;
}
