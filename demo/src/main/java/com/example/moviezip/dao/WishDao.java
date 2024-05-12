package com.example.moviezip.dao;

import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.Review;

import java.util.List;

public interface WishDao {
    List<Movie> getWishMovie(int id); // 찜한 영화 가져오기
    List<Review> getWishReview(int id); // 찜한 리뷰 가져오기
    List<Review> getMyReview(String userId); // 내가 쓴 리뷰 가져오기
}
