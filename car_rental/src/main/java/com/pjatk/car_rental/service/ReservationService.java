package com.pjatk.car_rental.service;

import com.pjatk.car_rental.model.Reservation;
import com.pjatk.car_rental.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }

    public Reservation saveReservation(Reservation reservation){
        reservationRepository.save(reservation);
        return reservation;
    }

    public void deleteReservation(Reservation reservation){
        reservationRepository.delete(reservation);
    }
}
