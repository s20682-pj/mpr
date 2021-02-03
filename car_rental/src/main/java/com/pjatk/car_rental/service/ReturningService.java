package com.pjatk.car_rental.service;

import com.pjatk.car_rental.exception.ReturningException;
import com.pjatk.car_rental.model.Car;
import com.pjatk.car_rental.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReturningService {
    private final CarRepository carRepository;

    public ReturningService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Optional<Car> returnCar(Long id) throws ReturningException {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()){
            int rented = car.get().getRented();
            if(rented == 0){
                System.out.println("Car is not rented");
            }
            else{
                car.get().setRented(0);
                System.out.println("You returned the car");
            }
        }
        else{
            throw new ReturningException();
        }
        return car;
    }
}
