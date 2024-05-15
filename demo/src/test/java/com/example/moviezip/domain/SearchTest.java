package com.example.moviezip.domain;

import com.example.moviezip.dao.mybatis.MybatisUserDao;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SearchTest {

    @Autowired
    private MybatisUserDao mybatisUserDao;

    @Test
    public void testSearchMovieByKeyword() throws Exception {
        // Given
        // String keyword = "파";

        // When
        List<Movie> movies = mybatisUserDao.searchMovieByKeyword("파");
        assertNotNull(movies);
        System.out.println("검색결과 가져오기");


        if (!movies.isEmpty()) {

            for (Movie movie : movies) {

                if (movie != null) {

                    System.out.println("MvId: " + movie.getMvId());
                    System.out.println("Title: " + movie.getMvTitle());
                    System.out.println("Star: " + movie.getMvStar());
                    System.out.println("MvDirector: " + movie.getMvDirector());
                    System.out.println("MvDetail: " + movie.getMvDetail());

                    System.out.println("--------------------------");
                } else {

                    System.out.println("Movie is null");
                }
            }
        } else {
            System.out.println("No search results found");
        }
        System.out.println();
    }

}