package com.example.moviezip.domain;

public class Review {
    private int mvId;
    private int rvId;
    private int rvStar;
    private String mvTitle;
    private String wrtier;
    private String content;
    private String rvTitle;

    public Review(){

    }

    public Review(String content, int star, int mvId) { // id 지웠음, 예진이 수정 필요
        this.content = content;
        this.rvStar = star;
        this.mvId = mvId;
    }
}
