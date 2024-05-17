package com.example.moviezip.domain;

import com.example.moviezip.dao.ReservationDao;
import com.example.moviezip.dao.mybatis.MybatisReservationDao;
import com.example.moviezip.service.ReservationImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ReservationTest {
    @Autowired
    ReservationDao reservationDao;

    @Autowired
    private MybatisReservationDao mybatisReservationDao;

    @Test
    public void testReservation() throws Exception {
        ReservationImpl rsvImpl = new ReservationImpl(mybatisReservationDao);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2024-05-17");

        Reservation reservation = new Reservation(3L, 2L, date, "A1");

        rsvImpl.insertReservation(reservation);
    }
}
