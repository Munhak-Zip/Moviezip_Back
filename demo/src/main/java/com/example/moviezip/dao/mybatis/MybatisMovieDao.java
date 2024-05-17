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
}
