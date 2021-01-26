package com.pjatk.car_rental.service;

import com.pjatk.car_rental.model.Car;
import com.pjatk.car_rental.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll(){
        return carRepository.findAll();
    }

    public Car saveCar(Car car){
        carRepository.save(car);
        return car;
    }

    public void deleteCar(Car car){
        carRepository.delete(car);
    }
}
