package com.example.moviezip.dao;

import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.Review;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WishDao {
    List<Movie> getWishMovie(long id); // 찜한 영화 가져오기
    List<Review> getWishReview(long id); // 찜한 리뷰 가져오기
    int deleteWishReview(@Param("id") long id, @Param("rvId") long rvId);
    List<Review> getMyReview(String userId); // 내가 쓴 리뷰 가져오기

    int saveWishMovie(int userid, int movie_id); //영화 찜하기
}
