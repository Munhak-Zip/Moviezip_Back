package com.example.moviezip.dao.mybatis;

import com.example.moviezip.dao.ReservationDao;
import com.example.moviezip.dao.mybatis.mapper.ReservationMapper;
import com.example.moviezip.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisReservationDao implements ReservationDao {
    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public void insertReservation(Reservation reservation) throws DataAccessException {
        reservationMapper.insertReservation(reservation);
    }

    @Override
    public int updateReservation(Reservation reservation) throws DataAccessException {
        return reservationMapper.updateReservation(reservation);
    }

}
