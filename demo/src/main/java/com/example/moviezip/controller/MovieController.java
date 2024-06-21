package com.example.moviezip.controller;

import com.example.moviezip.domain.Movie;
import com.example.moviezip.service.MovieImpl;
import com.example.moviezip.service.WishImpl;
import com.example.moviezip.service.recommend.MovieRecommenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/")
public class MovieController {
    private MovieImpl movieService;
    private WishImpl wishService;

    @Autowired
    public void setWishService(WishImpl wishService) {
        this.wishService = wishService;
    }
    @Autowired
    public void setMovieService(MovieImpl movieService) {
        this.movieService = movieService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("movie/{mvId}")
    public Movie getMovie(@PathVariable int mvId) throws Exception {
        System.out.println("Entering getMovie method with mvId: " + mvId);
        Movie mv = movieService.getAllMoviedetail(mvId);
        if (mv != null) {
            System.out.println("영화제목222: " + mv.getMvTitle());
            System.out.println("영화제목: " + mv.getMvStar());
            System.out.println(mv.getMvDetail());
            System.out.println(mv.getMvId());
            System.out.println(mv.getOpenDate());
            System.out.println(mv.getMvDirector());
            System.out.println(mv.getGenre2());
        } else {
            System.out.println("영화 정보를 찾을 수 없습니다.");
        }
        return mv;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/main")
    public List<Movie> getRecentMovie() {
        List<Movie> movieList = movieService.getRecentMovie();
        for(Movie movie : movieList) {
            System.out.println("최신영화"+movie.getMvTitle());
        }
        return movieList;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("search/{mvTitle}")
    public List<Movie> getSearchMovie(@PathVariable String mvTitle) throws Exception {
        System.out.println("Entering getMovie method with mvTitle: " + mvTitle);
        List<Movie> movieList = movieService.searchMoviesByTitle(mvTitle);
        for(Movie mv : movieList) {
            System.out.println("검색제목검색: " + mv.getMvTitle());
            System.out.println("검색제목별점: " + mv.getMvStar());
            System.out.println(mv.getMvId());
            System.out.println(mv.getMvDirector());
        }
        return movieList;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("movie/{mvId}/wish")
    public int checkMyMovieWish(@PathVariable int mvId, @RequestParam int userId) throws Exception {
        System.out.println("Entering getMovie method with mvId: " + mvId);
        int ch = wishService.checkMyWish(userId, mvId);
        System.out.println("찜" + ch);
        return ch;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("movie/{mvId}/wish/add")
    public int insertMyMovieWish(@PathVariable int mvId, @RequestParam int userId) throws Exception {
        System.out.println("Entering getMovie method with mvId: " + mvId);
        int ch = wishService.saveWishMovie(userId, mvId);
        if(ch == 1){
            System.out.println("찜 성공" + ch);
        }else {
            System.out.println("찜 실패");
        }
        return ch;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("movie/{mvId}/wish/delete")
    public int deleteMyMovieWish(@PathVariable int mvId, @RequestParam int userId) throws Exception {
        System.out.println("Entering getMovie method with mvId: " + mvId);
        int ch = wishService.deleteWishMovie(userId, mvId);
        if(ch == 1){
            System.out.println("찜 삭제 성공" +ch);
        }else {
            System.out.println("찜 삭제 실패");
        }
        return ch;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/movie/wish")
    public List<Movie> getWishMovie(@RequestParam int userId) {
        List<Movie> movieList = wishService.getWishMovie(userId);
        for (Movie movie : movieList) {
            System.out.println("보관함 영화: " + movie.getMvTitle());
        }
        // movieList 중 5개만 movieList2에 넣어서 return
        List<Movie> movieList2 = movieList.stream().limit(5).toList();
        return movieList2;
    }
}