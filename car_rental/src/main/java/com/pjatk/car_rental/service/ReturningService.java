package com.pjatk.car_rental.service;

import com.pjatk.car_rental.exception.ReturningException;
import com.pjatk.car_rental.model.Car;
import com.pjatk.car_rental.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReturningService {
    private final CarRepository carRepository;
    private final CarService carService;

    public ReturningService(CarRepository carRepository, CarService carService) {
        this.carRepository = carRepository;
        this.carService = carService;
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
                carService.saveCar(car.get());
                System.out.println("You returned the car");
            }
        }
        else{
            throw new ReturningException();
        }
        return car;
    }
}
