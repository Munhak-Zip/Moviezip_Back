package com.example.moviezip.domain;

import java.io.Serializable;

public class Review extends Movie {
    private int rvId;
    private int rvStar;
    private String mvTitle;
    private String wrtier;
    private String content;
    private String rvTitle;


    public Review(String content, int rvStar, Long mvId) { // id 지웠음, 예진이 수정 필요 -> 완료
        this.content = content;
        this.rvStar = rvStar;
        super.setMvId(mvId);
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

    public String getWrtier() {
        return wrtier;
    }

    public void setWrtier(String wrtier) {
        this.wrtier = wrtier;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRvTitle() {
        return rvTitle;
    }

    public void setRvTitle(String rvTitle) {
        this.rvTitle = rvTitle;
    }
}
