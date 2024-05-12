package com.example.moviezip.dao;

import com.example.moviezip.domain.Review;
import org.springframework.dao.DataAccessException;

public interface ReviewDao {

    void insertMyReview(Review review) throws DataAccessException;

}
