package com.example.moviezip.dao;

import com.example.moviezip.domain.Movie;

import java.util.List;

public interface MovieDao {
    //영화 검색
    public List<Movie> searchMovieByKeyword(String keyword);

}
