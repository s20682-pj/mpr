package com.pjatk.car_rental.controller;

import com.pjatk.car_rental.model.Car;
import com.pjatk.car_rental.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAll(){
        return ResponseEntity.ok(carService.findAll());
    }

    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car car){
        return ResponseEntity.ok(carService.saveCar(car));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCar(@RequestBody Car car){
        carService.deleteCar(car);
        return ResponseEntity.ok().build();
    }
}
