package com.example.moviezip.controller;

import com.example.moviezip.domain.Review;
import com.example.moviezip.domain.User;
import com.example.moviezip.service.ReviewImpl;
import com.example.moviezip.service.UserService;
import com.example.moviezip.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ReviewController {

    private final ReviewImpl reviewImpl;
    private final UserService userService;

    @Autowired
    public ReviewController(ReviewImpl reviewImpl, UserService userService) {
        this.reviewImpl = reviewImpl;
        this.userService = userService;

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("movie/{mvId}/regReview")
    public void insertMyReview(@RequestBody Review review, @PathVariable Long mvId) {
        try {
            review.setMvId(mvId);
            review.setIs_Critic("N");
            reviewImpl.insertMyReview(review);
            System.out.println("리뷰가 성공적으로 저장되었습니다: " + review.getContent());
        } catch (Exception e) {
            System.out.println("실패");
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("wish/myReviewList")
    public List<Review> getMyReviewList(@RequestParam String userId) {
        User user = userService.getUserById(userId);
        String wrtier = user.getUserId();

        List<Review> myReviewList = reviewImpl.getMyReviewList(wrtier);
        try {
            for (Review rv : myReviewList) {
                System.out.println("내가 쓴 리뷰 :" + rv.getMvTitle()+rv.getContent());
            }
        } catch (Exception e) {
            System.out.println("리뷰 실패:");
            e.printStackTrace();
        }
        return myReviewList;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("{mvId}/critics")
    public List<Review> getCriticsReview(@PathVariable int mvId) throws Exception {
        System.out.println("Entering getMovie method with mvId: " + mvId);
        List<Review> rv = reviewImpl.getCriticReviews(mvId);

        for (Review r : rv) {
            System.out.println("내용" + r.getContent());
            System.out.println("이름" + r.getRvTitle());
            System.out.println("별점" + r.getRvStar());
        }
        return rv;
    }
}
