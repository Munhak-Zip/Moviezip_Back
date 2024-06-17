package com.example.moviezip.controller;

import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.Reservation;
import com.example.moviezip.service.MovieImpl;
import com.example.moviezip.service.ReservationImpl;
import org.apache.hadoop.yarn.api.records.ReservationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ReservationController {
    private ReservationImpl reservationImpl;

    @Autowired
    public void setMovieService(ReservationImpl reservationImpl) {
        this.reservationImpl = reservationImpl;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("movie/reserve")
    public int insertReserve(@RequestBody Reservation request) throws Exception {
        System.out.println("Entering getMovie method with mvId: " + request);
        Reservation reservation = new Reservation(
                request.getMvId(),
                156L, // 예약 ID (임의로 설정하거나 관리할 수 있는 방법으로 설정)
                request.getDate(),
                request.getSeat()
        );

        reservationImpl.insertReservation(reservation);

        return 1;
    }
}
