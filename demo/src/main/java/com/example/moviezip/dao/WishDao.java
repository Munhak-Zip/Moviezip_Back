package com.example.moviezip.dao;

import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.Review;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface WishDao {
    List<Movie> getWishMovie(long id); // 찜한 영화 가져오기
    List<Review> getWishReview(long id); // 찜한 리뷰 가져오기
    int deleteWishReview(@Param("id") long id, @Param("rvId") long rvId);
    List<Review> getMyReview(String userId); // 내가 쓴 리뷰 가져오기

    int saveWishMovie(int id, int movieId); //영화 찜하기

    //영화 찜하기
    int saveWishMovie(long id, long movieId) throws DataAccessException;

    int checkMyWish(long id, int mvId);

    int checkMyWish(@Param("id")long id, @Param("movieId")long movieId);
    int deleteWishMovie(@Param("id")long id, @Param("movieId")long movieId);

}
