package com.example.moviezip.service;

import com.example.moviezip.dao.mybatis.MybatisWishDao;
import org.springframework.stereotype.Service;

@Service
public class WishImpl {

    private final MybatisWishDao mybatisWishDao;

    public WishImpl(MybatisWishDao mybatisWishDao) {
        this.mybatisWishDao = mybatisWishDao;
    }

    //영화 찜하기
    public int saveWishMovie(int userid, int movie_id){
        return mybatisWishDao.saveWishMovie(userid,movie_id);
    }

}
