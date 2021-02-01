package com.pjatk.car_rental.service;

import com.pjatk.car_rental.exception.CarException;
import com.pjatk.car_rental.model.Car;
import com.pjatk.car_rental.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<Car> cars = carRepository.findAll();
        if (cars.size() > 0){
            return cars.stream().filter(car -> car.getRented() == 0).collect(Collectors.toList());
        }
        else{
            throw new CarException();
        }
    }

    public List<Car> showRented() throws CarException{
        List<Car> cars = carRepository.findAll();
        if (cars.size() > 0){
            return cars.stream().filter(car -> car.getRented() == 1).collect(Collectors.toList());
        }
        else{
            throw new CarException();
        }
    }

    public List<Car> showOlderThan(Date date) throws CarException{
        List<Car> cars = carRepository.findAll();
        if (cars.size() > 0){
            return cars.stream().filter(car -> car.getManufacture_date().before(date)).collect(Collectors.toList());
        }
        else{
            throw new CarException();
        }
    }

    public List<Car> showYoungerThan(Date date) throws CarException{
        List<Car> cars = carRepository.findAll();
        if (cars.size() > 0){
            return cars.stream().filter(car -> car.getManufacture_date().after(date)).collect(Collectors.toList());
        }
        else{
            throw new CarException();
        }
    }

}
