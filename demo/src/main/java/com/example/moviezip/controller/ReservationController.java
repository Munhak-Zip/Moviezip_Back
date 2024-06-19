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

        System.out.println("아이디사용ㄷ자" + request.getId());
        Reservation reservation = new Reservation(
                request.getMvId(),
                request.getId(),
                request.getDateR(),
                request.getSeat(),
                request.getTime()
                );

        reservationImpl.insertReservation(reservation);

        return 1;
    }

    @GetMapping("user/mypage")
    public List<Reservation> getReservationById(@RequestParam Long userId) throws Exception {
        List<Reservation> reservations = reservationImpl.getReservationById(userId);
        System.out.println("아이디사용ㄷ자" + userId);
        for (Reservation res : reservations) {
            System.out.println("Title: " + res.getMvTitle());
            System.out.println("Date: " + res.getDateR());
            System.out.println("Time: " + res.getTime());
            System.out.println("Seat: " + res.getSeat());
        }
        return reservations;
    }
}
