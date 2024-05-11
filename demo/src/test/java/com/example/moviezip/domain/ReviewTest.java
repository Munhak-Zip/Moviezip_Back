package com.example.moviezip.domain;

import com.example.moviezip.dao.ReviewDao;
import com.example.moviezip.dao.mybatis.MybatisReviewDao;
import com.example.moviezip.service.ReviewImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@SpringBootTest
@ExtendWith(SpringExtension.class) // JUnit 5에서 Spring 테스트와의 통합을 위해 사용
public class ReviewTest {
    @Autowired
    ReviewDao reviewDao;

    @Autowired
    private MybatisReviewDao mybatisReviewDao;

    @Test
    public void testReview() throws Exception{
        System.out.println("테스트");

        Review review = new Review("0511", 1,2);
        ReviewImpl rvImpl = new ReviewImpl(mybatisReviewDao);

        rvImpl.insertMyReview(review);
    }
}
