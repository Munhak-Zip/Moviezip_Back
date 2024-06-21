package com.example.moviezip.service;

import com.example.moviezip.dao.mybatis.MybatisWishDao;
import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.Review;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishImpl {
    public final MybatisWishDao mybatisWishDao;

    public WishImpl(MybatisWishDao mybatisWishDao) {
        this.mybatisWishDao = mybatisWishDao;
    }

    public List<Movie> getWishMovie(long id){
        return mybatisWishDao.getWishMovie(id);
    }

    public List<Review> getWishReview(long id){
        return mybatisWishDao.getWishReview(id);
    }

    public int deleteWishReview(long id, long rvId){
        return mybatisWishDao.deleteWishReview(id, rvId);
    }

    public List<Review> getMyReview(String userId){
        return mybatisWishDao.getMyReview(userId);
    }

    public int checkMyWish(long id, long movieId){ return mybatisWishDao.checkMyWish(id, movieId);}

    public int saveWishMovie(long id, long movieId){return mybatisWishDao.saveWishMovie(id, movieId);}

    public int deleteWishMovie(long id, long movieId){return mybatisWishDao.deleteWishMovie(id, movieId);}
}
