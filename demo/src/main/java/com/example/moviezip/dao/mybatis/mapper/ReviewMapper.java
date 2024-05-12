package com.example.moviezip.dao.mybatis.mapper;

import com.example.moviezip.domain.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    void insertMyReview(Review review);
    void deleteReview(long rvId);
    List<Review> getReviewList(long mvId);
    Review getReviewDetail(long rvId);
    int updateReview(Review review);
}
