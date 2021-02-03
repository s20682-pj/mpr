package com.pjatk.car_rental.service;

import com.pjatk.car_rental.exception.CarException;
import com.pjatk.car_rental.exception.CustomerExpection;
import com.pjatk.car_rental.exception.ReservationException;
import com.pjatk.car_rental.model.Car;
import com.pjatk.car_rental.model.Customer;
import com.pjatk.car_rental.repository.CarRepository;
import com.pjatk.car_rental.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
    @Mock
    private CarRepository carRepository;

    @Mock
    private CarService carService;

    @Mock
    private Car car;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private Customer customer;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    public void setCar(){
        Date date1 = new Date(2020-01-01);
        car = new Car(1L,"bmw","a","aaa",date1,0);
    }

    @BeforeEach
    public void setCustomer(){
        customer = new Customer(1L,"a","b","111111",0);;
    }

    @AfterEach
    public void deleteCar() throws CarException{
        carService.deleteAll();
    }

    @AfterEach
    public void deleteCustomer() throws CustomerExpection{
        customerService.deleteAll();
    }

    @Test
    public void correctlyCheckIfRented() throws ReservationException{
        int rented = car.getRented();
        int value = 0;

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        Optional<Car> testCar = reservationService.checkIfRented(car.getId());

        assertThat(rented).isEqualTo(value);
    }

    @Test
    public void checkIfRentedException(){
        assertThatExceptionOfType(ReservationException.class).isThrownBy(() -> reservationService.checkIfRented(car.getId()));
    }

    @Test
    public void checkIfRentedIfRentedIsNotZero() throws ReservationException{
        car.setRented(1);
        int rented = car.getRented();

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        Optional<Car> testCar = reservationService.checkIfRented(car.getId());

        assertThat(rented).isEqualTo(car.getRented());
    }

    @Test
    public void rentCarProperly(){

    }


}
