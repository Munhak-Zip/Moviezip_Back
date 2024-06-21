package com.example.moviezip.dao.mybatis.mapper;

import com.example.moviezip.domain.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    void insertMyReview(Review review);
    List<Review> getMyReviewList(String userId);
    void deleteReview(long rvId);
    List<Review> getReviewList(long mvId);
    Review getReviewDetail(int rvId);
    int updateReview(Review review);
    //평론가 리뷰 가져오기
    List<Review> getCriticReviews(long mvId);
}
