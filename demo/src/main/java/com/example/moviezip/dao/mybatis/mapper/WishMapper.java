package com.example.moviezip.dao.mybatis.mapper;

import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WishMapper {
     List<Movie> getWishMovie(long id);
     List<Review> getWishReview(long id);
     int deleteWishReview(@Param("id") long id, @Param("rvId") long rvId);
     List<Review> getMyReview(String userId);

     //영화 찜하기
     int saveWishMovie(@Param("id")long id, @Param("movieId")long movieId);

     int checkMyWish(@Param("id")long id, @Param("movieId")long movieId);
     int deleteWishMovie(@Param("id")long id, @Param("movieId")long movieId);
}
