package com.example.moviezip.domain;

import com.example.moviezip.dao.ReviewDao;
import com.example.moviezip.dao.mybatis.MybatisReviewDao;
import com.example.moviezip.service.ReviewImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class) // JUnit 5에서 Spring 테스트와의 통합을 위해 사용
public class ReviewTest {
    @Autowired
    ReviewDao reviewDao;

    @Autowired
    private MybatisReviewDao mybatisReviewDao;

    @Test
    public void testReview() throws Exception {
        System.out.println("테스트");
        ReviewImpl rvImpl = new ReviewImpl(mybatisReviewDao);

        /* insertTest */
        /*
        Review review = new Review("0511", 1,2);

        rvImpl.insertMyReview(review); */

        /* deleteTest */
        /*
        rvImpl.deleteReview(81);
         */

        /* ReviewListTest */
        /*
        List<Review> rvlist = rvImpl.getReviewList(1);

        for (Review review : rvlist ) {
            System.out.println(review.getRvTitle()+", "+review.getContent());
        }
        */

        /* reviewDetailTest */
        /*Review review = rvImpl.getReviewDetail(83);
        System.out.println(review.getContent()+", "+review.getRvId());*/

        /* updateReviewTest */
//        Review newRv = new Review("이 리뷰 수정하겠음", 4, 2L);
//        newRv.setRvId(105L);
//        int updatedRv = rvImpl.updateReview(newRv);
//        System.out.println(updatedRv);

    }

    @Test
    public void CriticTest() throws Exception {
        ReviewImpl rvImpl = new ReviewImpl(mybatisReviewDao);
        List<Review> critic_reviews;

        critic_reviews = rvImpl.getCriticReviews(3);


        if (!critic_reviews.isEmpty()) {

            for (Review review : critic_reviews) {

                if (review != null) {

                    System.out.println("영화 아이디: " + review.getMvId());
                    System.out.println("리뷰 제목: " + review.getRvTitle());
                    System.out.println("평점: " + review.getRvStar());
                    System.out.println("리뷰 내용: " + review.getContent());
                    System.out.println("작성자: " + review.getWriter());

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
