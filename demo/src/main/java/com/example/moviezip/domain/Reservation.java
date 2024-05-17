package com.example.moviezip.domain;

import java.util.Date;

public class Reservation extends Movie{
    private Long reserveId;
    private Long id;
    private Date date;
    private String seat;

    public Reservation(Long mvId, Long id, Date date, String seat) {
        super.setMvId(mvId);
        this.id = id;
        this.date = date;
        this.seat = seat;
    }
    public Reservation(Long mvId, String mvTitle, Integer mvStar, String mvDetail, String mvDirector, Long reserveId, Long id, Date date, String seat) {
        super(mvId, mvTitle, mvStar, mvDetail, mvDirector);
        this.reserveId = reserveId;
        this.id = id;
        this.date = date;
        this.seat = seat;
    }

    public Long getReserveId() {
        return reserveId;
    }

    public void setReserveId(Long reserveId) {
        this.reserveId = reserveId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
