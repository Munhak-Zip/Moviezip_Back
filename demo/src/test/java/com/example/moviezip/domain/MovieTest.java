package com.example.moviezip.domain;

import com.example.moviezip.dao.mybatis.MybatisMovieDao;
import com.example.moviezip.service.MovieImpl;
import com.example.moviezip.service.ReviewImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MovieTest {

    @Autowired
    MybatisMovieDao mybatisMovieDao;
    @Test
    public void RecentMoviesTest() throws Exception {
//        MovieImpl mvImpl = new MovieImpl(mybatisMovieDao);

        List<Movie> recentmovies = mybatisMovieDao.getRecentMovie();

        if (!recentmovies.isEmpty()) {

            for (Movie movie : recentmovies) {

                if (movie != null) {

                    System.out.println("영화 아이디: " + movie.getMvId());
                    System.out.println("영화 제목: " + movie.getMvTitle());
                    System.out.println("평점: " + movie.getMvStar());
                    System.out.println("영화 내용: " + movie.getMvDetail());
                    System.out.println("감독: " +movie.getMvDirector());
                    System.out.println("개봉일: " +movie.getOpenDate());

                    System.out.println("--------------------------");
                } else {

                    System.out.println("Review is null");
                }
            }
        } else {
            System.out.println("No search results found");
        }
        System.out.println();
    }
}
