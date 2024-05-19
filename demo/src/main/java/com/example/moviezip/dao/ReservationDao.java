package com.example.moviezip.dao;


import com.example.moviezip.domain.Reservation;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ReservationDao {
    void insertReservation(Reservation reservation) throws DataAccessException;
    int updateReservation(Reservation reservation) throws DataAccessException;
    void deleteReservation(Long reserveId) throws DataAccessException;
    List<Reservation> getReservationById(Long id) throws DataAccessException;
    Reservation getReservationDetail(Long reserveId) throws DataAccessException;
}
