package com.example.moviezip.controller;

import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.Reservation;
import com.example.moviezip.service.MovieImpl;
import com.example.moviezip.service.ReservationImpl;
import org.apache.hadoop.yarn.api.records.ReservationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                request.getDateR(),
                request.getSeat(),
                request.getTime()
                );

        reservationImpl.insertReservation(reservation);

        return 1;
    }

    @GetMapping("user/mypage")
    public List<Reservation> getReservationById() throws Exception {
        Long userId = 156L; // 고정된 userId로 설정

        List<Reservation> reservations = reservationImpl.getReservationById(userId);
        for(Reservation res : reservations) {
            System.out.println("Title: " + res.getMvTitle());
            System.out.println("Date: " + res.getDateR());
            System.out.println("Time: " + res.getTime());
            System.out.println("Seat: " + res.getSeat());
        }
        return reservations;
    }
}
