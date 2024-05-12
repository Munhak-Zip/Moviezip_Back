package com.example.moviezip.domain;

import java.util.List;

public class Movie {
    private Long mvId;
    private String mvTitle;
    private int mvStar;
    private String mvDetail;
    private List<String> genre;
    private List<Review> review;
    private String mvDirector;

    public Movie() {
    }

    public Long getMvId() {
        return mvId;
    }

    public void setMvId(Long mvId) {
        this.mvId = mvId;
    }

    public String getMvTitle() {
        return mvTitle;
    }

    public void setMvTitle(String mvTitle) {
        this.mvTitle = mvTitle;
    }

    public int getMvStar() {
        return mvStar;
    }

    public void setMvStar(int mvStar) {
        this.mvStar = mvStar;
    }

    public String getMvDetail() {
        return mvDetail;
    }

    public void setMvDetail(String mvDetail) {
        this.mvDetail = mvDetail;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }
}
