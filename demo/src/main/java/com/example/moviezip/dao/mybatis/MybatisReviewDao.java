package com.example.moviezip.dao.mybatis;

import com.example.moviezip.dao.ReviewDao;
import com.example.moviezip.dao.mybatis.mapper.ReviewMapper;
import com.example.moviezip.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisReviewDao implements ReviewDao {
    @Autowired
    private ReviewMapper reviewMapper;
    @Override
    public void insertMyReview(Review review) throws DataAccessException {
        reviewMapper.insertMyReview(review);
    }
}
