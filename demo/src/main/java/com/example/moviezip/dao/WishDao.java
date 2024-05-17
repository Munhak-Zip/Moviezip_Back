package com.example.moviezip.dao;

import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.Review;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WishDao {
    List<Movie> getWishMovie(int id); // 찜한 영화 가져오기
    List<Review> getWishReview(int id); // 찜한 리뷰 가져오기
    int deleteWishReview(@Param("id") int id, @Param("rvId") int rvId);
    List<Review> getMyReview(String userId); // 내가 쓴 리뷰 가져오기
}
