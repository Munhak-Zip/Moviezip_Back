package com.example.moviezip.dao;

import com.example.moviezip.domain.Movie;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MovieDao {
    //영화 검색
    public List<Movie> searchMovieByKeyword(String keyword);

    //최신 영화 검색
    public List<Movie> getRecentMovie();

}
