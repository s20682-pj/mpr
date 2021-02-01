package com.pjatk.car_rental.controller;

import com.pjatk.car_rental.exception.CarException;
import com.pjatk.car_rental.model.Car;
import com.pjatk.car_rental.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAll() throws CarException{
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Car>> findById(@PathVariable Long id) throws CarException {
        Optional<Car> findById = carService.findById(id);
        if (findById.isPresent()) {
            return ResponseEntity.ok(findById);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car car) throws CarException{
        return ResponseEntity.ok(carService.saveCar(car));
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteAll() throws CarException{
        carService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws CarException{
        carService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
