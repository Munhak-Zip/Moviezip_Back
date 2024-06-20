package com.example.moviezip.dao.mybatis;

import com.example.moviezip.dao.WishDao;
import com.example.moviezip.dao.mybatis.mapper.WishMapper;
import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.Review;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MybatisWishDao implements WishDao {
    // WishDao 재정의 및 구현
    @Autowired
    private WishMapper wishMapper;

    @Override
    public List<Movie> getWishMovie(long id) throws DataAccessException{
        return wishMapper.getWishMovie(id);
    }

    @Override
    public List<Review> getWishReview(long id) throws DataAccessException{
        return wishMapper.getWishReview(id);
    }

    @Override
    public int deleteWishReview(long id, long rvId) throws DataAccessException{
        return wishMapper.deleteWishReview(id, rvId);
    }

    @Override
    public List<Review> getMyReview(String userId) throws DataAccessException{
        return wishMapper.getMyReview(userId);
    }

    @Override
    public int saveWishMovie(int id, int movieId) {
        return wishMapper.saveWishMovie(id,movieId);
    }

    //영화 찜하기
    @Override
    public int saveWishMovie(long id, long movieId) throws DataAccessException{
        return wishMapper.saveWishMovie(id,movieId);
    }

    @Override
    public int checkMyWish(long id, int movieId) {
        return wishMapper.checkMyWish(id, movieId);
    }

    @Override
    public int checkMyWish(long id, long movieId) throws DataAccessException {
        return wishMapper.checkMyWish(id, movieId);
    }

    @Override
    public int deleteWishMovie(@Param("id")long id, @Param("movieId")long movieId) throws DataAccessException{
        return wishMapper.deleteWishMovie(id, movieId);
    }
}
