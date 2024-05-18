package com.example.moviezip.dao.mybatis.mapper;

import com.example.moviezip.domain.Reservation;
import com.example.moviezip.domain.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    void insertReservation(Reservation reservation);
    int updateReservation(Reservation reservation);
    void deleteReservation(Long reserveId);
}
