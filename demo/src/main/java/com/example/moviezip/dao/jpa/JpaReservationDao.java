package com.example.moviezip.dao.jpa;


import com.example.moviezip.dao.ReservationDao;
import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.Reservation;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

//@Repository
//@Transactional
//public class JpaReservationDao implements ReservationDao {
//    @PersistenceContext
//    private EntityManager em;
//    @Override
//    public void insertReservation(Reservation reservation) throws DataAccessException {
//        em.persist(reservation);
//    }
//
//    @Override
//    public int updateReservation(Reservation reservation) throws DataAccessException {
//        Reservation existingReservation = em.find(Reservation.class, reservation.getReserveId());
//        if (existingReservation != null) {
//            existingReservation.setDate(reservation.getDate());
//            existingReservation.setSeat(reservation.getSeat());
//            em.merge(existingReservation);
//            return 1;
//        }
//        return 0;
//    }
//
//    @Override
//    public void deleteReservation(Long reserveId) throws DataAccessException {
//        Reservation reservation = em.find(Reservation.class, reserveId);
//        if (reservation != null) {
//            em.remove(reservation);
//        }
//    }
//
//    @Override
//    public List<Reservation> getReservationById(Long id) throws DataAccessException {
//        TypedQuery<Reservation> query = em.createQuery(
//                "SELECT r FROM Reservation r WHERE r.id = :id", Reservation.class);
//        query.setParameter("id", id);
//        return query.getResultList();
//    }
//
//    @Override
//    public Reservation getReservationDetail(Long reserveId) throws DataAccessException {
//        return em.find(Reservation.class, reserveId);
//    }
//}