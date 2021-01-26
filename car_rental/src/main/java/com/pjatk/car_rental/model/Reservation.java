package com.pjatk.car_rental.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @OneToOne
    private Car car;
    @OneToOne
    private Customer customer;
    private Date date_of_reservation;
    private double amount;
    private int payed;


    public Reservation(Long id, Car car, Customer customer, Date date_of_reservation, double amount, int payed) {
        this.id = id;
        this.car = car;
        this.customer = customer;
        this.date_of_reservation = date_of_reservation;
        this.amount = amount;
        this.payed = payed;
    }

    public Reservation(Car car, Customer customer, Date date_of_reservation, double amount, int payed) {
        this.car = car;
        this.customer = customer;
        this.date_of_reservation = date_of_reservation;
        this.amount = amount;
        this.payed = payed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate_of_reservation() {
        return date_of_reservation;
    }

    public void setDate_of_reservation(Date date_of_reservation) {
        this.date_of_reservation = date_of_reservation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayed() {
        return payed;
    }

    public void setPayed(int payed) {
        this.payed = payed;
    }
}
