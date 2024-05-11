package com.example.moviezip.dao;

import com.example.moviezip.domain.Review;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ReviewDao {

    void insertMyReview(Review review) throws DataAccessException;

    void deleteReview(long rvId) throws DataAccessException;

    List<Review> getReviewList(long mvId) throws DataAccessException;
}
