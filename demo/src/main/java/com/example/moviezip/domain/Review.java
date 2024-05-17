package com.example.moviezip.domain;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Review implements Serializable {
    private int mvId;
    private int rvId;
    private int rvStar;
    private String writer;
    private String mvTitle;
    private String content;
    private String rvTitle;
    private String is_Critic;


    public Review() {
    }

    public Review(String content, int rvStar, int mvId) { // id 지웠음, 예진이 수정 필요 -> 완료
        this.content = content;
        this.rvStar = rvStar;
        this.mvId = mvId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRvTitle(String rvTitle) {
        this.rvTitle = rvTitle;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setIs_Critic(String is_Critic) {
        this.is_Critic = is_Critic;
    }

    public void setMvId(int mvId) {
        this.mvId = mvId;
    }

    public void setRvId(int rvId) {
        this.rvId = rvId;
    }

    public void setRvStar(int rvStar) {
        this.rvStar = rvStar;
    }

    public void setMvTitle(String mvTitle) {
        this.mvTitle = mvTitle;
    }
}
