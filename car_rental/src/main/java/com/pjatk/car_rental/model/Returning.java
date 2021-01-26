package com.pjatk.car_rental.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Returning {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @OneToOne
    private Reservation reservation;
    private Date date_of_return;

    public Returning(Long id, Reservation reservation, Date date_of_return) {
        this.id = id;
        this.reservation = reservation;
        this.date_of_return = date_of_return;
    }

    public Returning(Reservation reservation, Date date_of_return) {
        this.reservation = reservation;
        this.date_of_return = date_of_return;
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

    public Date getDate_of_return() {
        return date_of_return;
    }

    public void setDate_of_return(Date date_of_return) {
        this.date_of_return = date_of_return;
    }
}
