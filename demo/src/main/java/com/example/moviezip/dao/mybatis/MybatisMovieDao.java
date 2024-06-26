package com.example.moviezip.dao.mybatis;

import com.example.moviezip.dao.MovieDao;
import com.example.moviezip.dao.mybatis.mapper.MovieMapper;
import com.example.moviezip.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MybatisMovieDao implements MovieDao {

    @Autowired
    private MovieMapper moviemapper;

    @Transactional
    @Override
    public List<Movie> searchMovieByKeyword(String keyword) throws DataAccessException {

        return moviemapper.searchMovieByKeyword(keyword);
    }


    @Transactional
    @Override
    public List<Movie> getRecentMovie() throws DataAccessException {
        return moviemapper.getRecentMovie();
    }

    //영화 상세 조회

    @Transactional
    @Override
    public Movie getMoviedetail(int movie_id) throws DataAccessException {
        return moviemapper.getMoviedetail(movie_id);
    }

    @Transactional
    @Override
    public Movie getMovieTitle(String movie_title) throws DataAccessException {
        return moviemapper.getMovieTitle(movie_title);
    }

    @Transactional
    @Override
    public Movie getAllMoviedetail(int movie_id) throws DataAccessException {
        return moviemapper.getAllMoviedetail(movie_id);
    }
}
