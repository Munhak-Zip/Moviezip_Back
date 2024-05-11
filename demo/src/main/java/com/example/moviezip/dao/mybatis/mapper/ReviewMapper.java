package com.example.moviezip.dao.mybatis.mapper;

import com.example.moviezip.domain.Review;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    void insertMyReview(Review review);
    void deleteReview(long rvId);
}
