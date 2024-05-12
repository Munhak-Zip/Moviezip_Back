package com.example.moviezip;


import com.example.moviezip.dao.mybatis.MybatisWishDao;
import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.Review;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

//사용할 DB 선언
@SpringBootTest
@ExtendWith(SpringExtension.class)
//@RunWith(SpringJUnit4ClassRunner.class) //Unit 프레임워크에게 사용할 테스트 러너(runner)를 지정하는 데 사용
//@ContextConfiguration(locations = "file:demo/src/main/resources/application.properties")  //테스트 실행에 필요한 애플리케이션 컨텍스트를 구성하는 방법을 지정
//@MapperScan("com.example.moviezip.dao.mybatis.mapper") // MyBatis 매퍼 인터페이스 위치
public class WishTest {
    @Autowired
    private MybatisWishDao mybatisWishDao;

    @Test // 좋아하는 영화
    public void testGetWishMovie() throws Exception{
        List<Movie> movie1 = mybatisWishDao.getWishMovie(21);
        System.out.println("mih 찜한 영화 가져오기");
        for (Movie movie : movie1) {
            System.out.println("Title: " + movie.getMvTitle());
            System.out.println("Star: " + movie.getMvStar());
            System.out.println("--------------------------");
        }
        System.out.println();

        List<Movie> movie2 = mybatisWishDao.getWishMovie(3);
        System.out.println("dayun 찜한 영화 가져오기");
        for (Movie movie : movie2) {
            System.out.println("Title: " + movie.getMvTitle());
            System.out.println("Star: " + movie.getMvStar());
            System.out.println("--------------------------");
        }
        System.out.println();

        List<Movie> movie3 = mybatisWishDao.getWishMovie(2);
        System.out.println("HMY 찜한 영화 가져오기");
        for (Movie movie : movie3) {
            System.out.println("Title: " + movie.getMvTitle());
            System.out.println("Star: " + movie.getMvStar());
            System.out.println("--------------------------");
        }
    }

    @Test
    public void testGetWishReview() throws Exception{
        // 좋아하는 리뷰
        List<Review> review1 = mybatisWishDao.getWishReview(21);
        System.out.println("mih 찜한 평론가 리뷰 가져오기");
        for (Review review : review1) {
            System.out.println("Review Writer: " + review.getWriter());
            System.out.println("Review Star: " + review.getRvStar());
            System.out.println("Review Title: " + review.getRvTitle());
            System.out.println("Review Context: " + review.getContent());
            System.out.println("--------------------------");
        }
        System.out.println();

        List<Review> review2 = mybatisWishDao.getWishReview(3);
        System.out.println("dayun 찜한 평론가 리뷰 가져오기");
        for (Review review : review2) {
            System.out.println("Review Writer: " + review.getWriter());
            System.out.println("Review Star: " + review.getRvStar());
            System.out.println("Review Title: " + review.getRvTitle());
            System.out.println("Review Context: " + review.getContent());
            System.out.println("--------------------------");
        }
        System.out.println();

        List<Review> review3 = mybatisWishDao.getWishReview(2);
        System.out.println("HMY 찜한 평론가 리뷰 가져오기");
        for (Review review : review3) {
            System.out.println("Review Writer: " + review.getWriter());
            System.out.println("Review Star: " + review.getRvStar());
            System.out.println("Review Title: " + review.getRvTitle());
            System.out.println("Review Context: " + review.getContent());
            System.out.println("--------------------------");
        }
        System.out.println();
    }

    @Test // 좋아하는 리뷰 삭제
    public void testDeleteWishReview() throws Exception{
        // 삭제 전 출력
        System.out.println("삭제 전 출력");
        List<Review> review1 = mybatisWishDao.getWishReview(21);
        for (Review review : review1) {
            System.out.println("Review Writer: " + review.getWriter());
            System.out.println("Review Star: " + review.getRvStar());
            System.out.println("Review Title: " + review.getRvTitle());
            System.out.println("Review Context: " + review.getContent());
            System.out.println("--------------------------");
        }
        System.out.println();

        // 삭제 후 출력
        System.out.println("삭제 후 출력");
        int deleteWishReview1 = mybatisWishDao.deleteWishReview(21, 41);
        review1 = mybatisWishDao.getWishReview(21); // 삭제 후 다시 받아와야 함
        for (Review review : review1) {
            System.out.println("Review Writer: " + review.getWriter());
            System.out.println("Review Star: " + review.getRvStar());
            System.out.println("Review Title: " + review.getRvTitle());
            System.out.println("Review Context: " + review.getContent());
            System.out.println("--------------------------");
        }
        System.out.println();
    }


    @Test
    public void testGetMyReview() throws Exception{
    // 내가 작성한 리뷰
        List<Review> myReview1 = mybatisWishDao.getMyReview("mih");
        System.out.println("HMY 찜한 평론가 리뷰 가져오기");
        for (Review myReview : myReview1) {
            System.out.println("Movie Title: " + myReview.getMvTitle());
            System.out.println("MyReview Star: " + myReview.getRvStar());
            System.out.println("MyReview Context: " + myReview.getContent());
            System.out.println("--------------------------");
        }
        System.out.println();
    }


}
