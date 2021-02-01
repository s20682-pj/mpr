package com.pjatk.car_rental.controller;

import com.pjatk.car_rental.exception.CarException;
import com.pjatk.car_rental.model.Car;
import com.pjatk.car_rental.service.CarService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping("/notRented")
    public ResponseEntity<List<Car>> showNotRented() throws CarException {
        return ResponseEntity.ok(carService.showNotRented());
    }

    @GetMapping("/rented")
    public ResponseEntity<List<Car>> showRented() throws CarException {
        return ResponseEntity.ok(carService.showRented());
    }

    @GetMapping("/older/{date}")
    public ResponseEntity<List<Car>> showOlderThan(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) throws CarException {
        return ResponseEntity.ok(carService.showOlderThan(date));
    }

    @GetMapping("/younger/{date}")
    public ResponseEntity<List<Car>> showYoungerThan(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) throws CarException {
        return ResponseEntity.ok(carService.showYoungerThan(date));
    }
}
