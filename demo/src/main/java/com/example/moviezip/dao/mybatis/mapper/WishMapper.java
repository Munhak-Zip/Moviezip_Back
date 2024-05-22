package com.example.moviezip.dao.mybatis.mapper;

import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WishMapper {
     List<Movie> getWishMovie(int id);
     List<Review> getWishReview(int id);
     int deleteWishReview(@Param("id") int id, @Param("rvId") int rvId);
     List<Review> getMyReview(String userId);

     //영화 찜하기
     int saveWishMovie(int userid, int movie_id);
}
