package com.example.moviezip.domain;

public class Movie {
    private Long mvId;
    private String mvTitle;
    private int mvStar;
    private String mvDetail;

    private String mvDirector;

    public String getMvDirector() {
        return mvDirector;
    }

    public void setMvDirector(String mvDirector) {
        this.mvDirector = mvDirector;
    }

    //    private List<String> genre;
//    private List<Review> review;

    public Movie() {

    }

    public Movie(Long mvId, String mvTitle, Integer mvStar, String mvDetail, String mvDirector) {
        this.mvId = mvId;
        this.mvTitle = mvTitle;
        this.mvStar = mvStar;
        this.mvDetail = mvDetail;
        this.mvDirector = mvDirector;
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

    public Integer getMvStar() {
        return mvStar;
    }

    public void setMvStar(Integer mvStar) {
        this.mvStar = mvStar;
    }

    public String getMvDetail() {
        return mvDetail;
    }

    public void setMvDetail(String mvDetail) {
        this.mvDetail = mvDetail;
    }


    //
//    public List<String> getGenre() {
//        return genre;
//    }
//
//    public void setGenre(List<String> genre) {
//        this.genre = genre;
//    }

//    public List<Review> getReview() {
//        return review;
//    }
//
//    public void setReview(List<Review> review) {
//        this.review = review;
//    }
}
