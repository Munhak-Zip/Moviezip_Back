package com.example.moviezip.domain;

import java.util.Date;

public class Reservation {
    private Long reserveId;
    private Date date;

    public Reservation(Long reserveId, Date date, String seat) {
        this.reserveId = reserveId;
        this.date = date;
        this.seat = seat;
    }

    private String seat;

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
