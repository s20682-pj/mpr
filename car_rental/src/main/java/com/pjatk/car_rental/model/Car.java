package com.pjatk.car_rental.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Car {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String brand;
    private String model;
    private String plate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date manufacture_date;
    private int rented;

    public Car(Long id, String brand, String model, String plate, Date manufacture_date, int rented) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.manufacture_date = manufacture_date;
        this.rented = rented;
    }

    public Car(String brand, String model, String plate, Date manufacture_date, int rented) {
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.manufacture_date = manufacture_date;
        this.rented = rented;
    }

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getRented() {
        return rented;
    }

    public void setRented(int rented) {
        this.rented = rented;
    }

    public Date getManufacture_date() {
        return manufacture_date;
    }

    public void setManufacture_date(Date manufacture_date) {
        this.manufacture_date = manufacture_date;
    }
}
