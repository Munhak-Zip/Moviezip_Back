package com.example.moviezip.domain;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {

    private Long mvId;
    private String mvTitle;
    private int mvStar;
    private String mvDetail;
    private List<String> genre;
    private List<Review> review;
    private String mvDirector;
    private String openDate;
    private String mvImg;
    private String genre2;

    public Movie() {

    }

    public Movie(Long mvId, String mvTitle, Integer mvStar, String mvDetail, String mvDirector,String mvImg) {
        this.mvId = mvId;
        this.mvTitle = mvTitle;
        this.mvStar = mvStar;
        this.mvDetail = mvDetail;
        this.mvDirector = mvDirector;
        this.mvImg = mvImg;
    }

    public Movie(Long mvId, String mvTitle, Integer mvStar, String mvDetail, String mvDirector,String mvImg, String genre) {
        this.mvId = mvId;
        this.mvTitle = mvTitle;
        this.mvStar = mvStar;
        this.mvDetail = mvDetail;
        this.mvDirector = mvDirector;
        this.mvImg = mvImg;
        this.genre2 = genre;
    }

    public void setMvId(Long mvId) {
        this.mvId = mvId;
    }

    public void setMvTitle(String mvTitle) {
        this.mvTitle = mvTitle;
    }

    public void setMvStar(Integer mvStar) {
        this.mvStar = mvStar;
    }

    public void setMvDetail(String mvDetail) {
        this.mvDetail = mvDetail;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public void setMvStar(int mvStar) {
        this.mvStar = mvStar;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    public void setMvDirector(String mvDirector) {
        this.mvDirector = mvDirector;
    }
    public void setMvImg(String mvImg) {this.mvImg = mvImg; }
    public void setGenre2(String genre2) {this.genre2 = genre2; }

    public Long getMvId() {
        return mvId;
    }

    public String getMvTitle() {
        return mvTitle;
    }

    public int getMvStar() {
        return mvStar;
    }

    public String getMvDetail() {
        return mvDetail;
    }

    public List<String> getGenre() {
        return genre;
    }

    public List<Review> getReview() {
        return review;
    }

    public String getMvDirector() {
        return mvDirector;
    }

    public String getOpenDate() {
        return openDate;
    }
    public String getMvImg() { return mvImg; }
    public String getGenre2() { return genre2; }
}
