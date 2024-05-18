package com.example.moviezip.domain;

import lombok.Getter;

import java.io.Serializable;

public class Review extends Movie {
    private int rvId;
    private int rvStar;
    private String writer;
    private String mvTitle;
    private String content;
    private String rvTitle;
    private String is_Critic;



    public Review(String content, int rvStar, Long mvId) { // id 지웠음, 예진이 수정 필요 -> 완료
        this.content = content;
        this.rvStar = rvStar;
        super.setMvId(mvId);
        super.setMvId(mvId);
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


    public int getRvId() {
        return rvId;
    }

    public void setRvId(int rvId) {
        this.rvId = rvId;
    }

    public int getRvStar() {
        return rvStar;
    }

    public void setRvStar(int rvStar) {
        this.rvStar = rvStar;
    }

    public String getMvTitle() {
        return mvTitle;
    }

    public void setMvTitle(String mvTitle) {
        this.mvTitle = mvTitle;
    }

    public String getWriter() {
        return writer;
    }

    public String getIs_Critic() {
        return is_Critic;
    }

    public String getContent() {
        return content;
    }


    public String getRvTitle() {
        return rvTitle;
    }

}
