package com.pjatk.car_rental.service;

import com.pjatk.car_rental.exception.CarException;
import com.pjatk.car_rental.model.Car;
import com.pjatk.car_rental.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll() throws CarException{
        if (carRepository.findAll().size() > 0){
            return carRepository.findAll();
        } else{
            throw new CarException();
        }
    }

    public Optional<Car> findById(Long id) throws CarException {
        if (carRepository.findById(id).isPresent()){
            return carRepository.findById(id);
        }
        else {
            throw new CarException();
        }
    }

    public Car saveCar(Car car) throws CarException{
        if (car.getRented() != 0) {
            throw new CarException();
        }
        else{
            return carRepository.save(car);
        }
    }

    public void deleteAll() throws CarException{
        if (carRepository.findAll().size() > 0){
            carRepository.deleteAll();
        } else{
            throw new CarException();
        }
    }

    public void deleteById(Long id) throws CarException{
        if (carRepository.findById(id).isPresent()){
            carRepository.deleteById(id);
        }
        else {
            throw new CarException();
        }
    }

    public List<Car> showNotRented() throws CarException{
        if (carRepository.findAll().size() > 0 || saveCar(Car).getRented() == 0){
            return carRepository.findAll();
        }
        else{
            throw new CarException();
        }
    }

}
