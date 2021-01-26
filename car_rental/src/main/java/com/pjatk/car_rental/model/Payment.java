package com.pjatk.car_rental.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @OneToOne
    private Reservation reservation;
    private Date date_of_payment;

    public Payment(Long id, Reservation reservation, Date date_of_payment) {
        this.id = id;
        this.reservation = reservation;
        this.date_of_payment = date_of_payment;
    }

    public Payment(Reservation reservation, Date date_of_payment) {
        this.reservation = reservation;
        this.date_of_payment = date_of_payment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Date getDate_of_payment() {
        return date_of_payment;
    }

    public void setDate_of_payment(Date date_of_payment) {
        this.date_of_payment = date_of_payment;
    }
}
