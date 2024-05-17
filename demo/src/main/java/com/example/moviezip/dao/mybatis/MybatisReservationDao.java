package com.example.moviezip.dao.mybatis;

import com.example.moviezip.dao.mybatis.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisReservationDao {
    @Autowired
    private ReservationMapper reservationMapper;

}
