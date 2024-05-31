package com.example.moviezip.service;

import com.example.moviezip.dao.mybatis.MybatisMovieDao;
import com.example.moviezip.domain.Movie;

import java.util.List;

public class MovieImpl {

    public MybatisMovieDao mybatisMovieDao;

    public List<Movie> searchMoviesByTitle(String keyword) {

        return mybatisMovieDao.searchMovieByKeyword(keyword);
    }
}
