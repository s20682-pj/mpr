package com.pjatk.car_rental.controller;

import com.pjatk.car_rental.model.Reservation;
import com.pjatk.car_rental.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> findAll(){
        return ResponseEntity.ok(reservationService.findAll());
    }

    @PostMapping
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation){
        return ResponseEntity.ok(reservationService.saveReservation(reservation));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteReservation(@RequestBody Reservation reservation){
        reservationService.deleteReservation(reservation);
        return ResponseEntity.ok().build();
    }
}
