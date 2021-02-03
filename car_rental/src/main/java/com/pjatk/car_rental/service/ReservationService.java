package com.pjatk.car_rental.service;

import com.pjatk.car_rental.exception.CarException;
import com.pjatk.car_rental.exception.CustomerExpection;
import com.pjatk.car_rental.exception.ReservationException;
import com.pjatk.car_rental.model.Car;
import com.pjatk.car_rental.model.Customer;
import com.pjatk.car_rental.repository.CarRepository;
import com.pjatk.car_rental.repository.CustomerRepository;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ReservationService {
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private CarService carService;
    private CustomerService customerService;

    public ReservationService(CarRepository carRepository, CustomerRepository customerRepository, CarService carService, CustomerService customerService) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.carService = carService;
        this.customerService = customerService;
    }

    public Optional<Car> checkIfRented(Long id) throws ReservationException {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()){
            int rented = car.get().getRented();
            if(rented == 1){
                System.out.println("Car is not available to rent");
            }
            else{
                System.out.println("Car is available to rent");
            }
        }
        else{
            throw new ReservationException();
        }
        return car;
    }

    public Optional<Car> rentCar(Long carId, Long customerId) throws ReservationException, CarException, CustomerExpection {
        Optional<Car> carToRent = carRepository.findById(carId);
        Optional<Customer> rentingCustomer = customerRepository.findById(customerId);
        if (carToRent.isPresent() && rentingCustomer.isPresent()){
            int rents = rentingCustomer.get().getRents();
            int rented = carToRent.get().getRented();
            if(rented == 0){
                carToRent.get().setRented(1);
                rentingCustomer.get().setRents(rents + 1);
                carService.saveCar(carToRent.get());
                customerService.saveCustomer(rentingCustomer.get());
                System.out.println("Car is rented");
            }else{
                System.out.println("Car is not available to rent or customer doesn't exist");
            }
        }else{
            throw new ReservationException();
        }
        return carToRent;
    }

}
