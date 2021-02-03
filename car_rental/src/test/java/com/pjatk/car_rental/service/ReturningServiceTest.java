package com.pjatk.car_rental.service;

import com.pjatk.car_rental.exception.ReturningException;
import com.pjatk.car_rental.model.Car;
import com.pjatk.car_rental.repository.CarRepository;
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
public class ReturningServiceTest {
    @Mock
    private CarRepository carRepository;

    @Mock
    private CarService carService;

    @Mock
    private Car car;

    @InjectMocks
    private ReturningService returningService;

    @BeforeEach
    public void setCar(){
        Date date1 = new Date(2020-01-01);
        car = new Car(1L,"bmw","a","aaa",date1,1);
    }

    @Test
    public void shouldReturnCorectly() throws ReturningException {
        int rented = car.getRented();
        int value = 0;

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        Optional<Car> testCar = returningService.returnCar(car.getId());

        assertThat(testCar.get().getRented()).isEqualTo(value);
    }

    @Test
    public void shouldReturnException(){
        assertThatExceptionOfType(ReturningException.class).isThrownBy(() -> returningService.returnCar(car.getId()));
    }

    @Test
    public void returnNotPossibleCarNotRented(){
        car.setRented(0);

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        assertThatExceptionOfType(ReturningException.class).isThrownBy(() -> returningService.returnCar(car.getId()));
    }

    @Test
    public void returnCarThatDoesntExist(){

        carRepository.deleteAll();

        assertThatExceptionOfType(ReturningException.class).isThrownBy(() -> returningService.returnCar(car.getId()));
    }
}
