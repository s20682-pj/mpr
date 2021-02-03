package com.pjatk.car_rental.service;

import com.pjatk.car_rental.exception.CarException;
import com.pjatk.car_rental.model.Car;
import com.pjatk.car_rental.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    private CarRepository carRepository;

    @Mock
    private Car car;

    @InjectMocks
    private CarService carService;

    public List<Car> carListTest(){
        Date date1 = new Date(2020-01-01);
        Date date2 = new Date(2000-01-01);
        Car car1 = new Car("bmw","a","aaa",date1,0);
        Car car2 = new Car(1L,"bmw","a","aaa",date2,0);
        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);
        return carList;
    }


    @Test
    void findAllCars() throws CarException {
        //given
        when(carRepository.findAll()).thenReturn(List.of(new Car()));
        //when
        List<Car> all = carService.findAll();
        //then
        assertThat(all).isNotEmpty();
    }

    @Test
    void shouldFindAllExpection(){
        assertThatExceptionOfType(CarException.class).isThrownBy(() -> carService.findAll());
    }

    @Test
    void findCarById() throws CarException{
        //given
        when(carRepository.findById(1L)).thenReturn(Optional.of(new Car()));
        //when
        Optional<Car> car = carService.findById(1L);
        //then
        assertThat(car).isNotEmpty();
    }

    @Test
    void shouldFindByIdExpection(){
        assertThatExceptionOfType(CarException.class).isThrownBy(() -> carService.findById(car.getId()));
    }

    @Test
    void saveNewCar() throws CarException{
        //given
        Date date1 = new Date(2020-01-01);
        Date date2 = new Date(2000-01-01);
        Car car1 = new Car("bmw","a","aaa",date1,0);
        Car car2 = new Car(1L,"bmw","a","aaa",date2,0);
        when(carRepository.save(car1)).thenReturn(car2);
        //when
        Car newCar = carService.saveCar(car1);
        //then
        assertThat(newCar.getId()).isEqualTo(car2.getId());
    }

    @Test
    void shouldDelete() throws CarException {
        //Given
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        Optional<Car> car = carService.findById(1L);
        //When
        carService.deleteById(1L);
        //Then
        verify(carRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldDeleteExpection(){
        assertThatExceptionOfType(CarException.class).isThrownBy(() -> carService.deleteById(car.getId()));
    }

    @Test
    void shouldDeleteAll() throws CarException{
        //given
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        when(carRepository.findAll()).thenReturn(cars);
        //when
        carService.deleteAll();
        //then
        verify(carRepository,atLeastOnce()).deleteAll();
    }

    @Test
    void shouldDeleteAllExpection() {
        assertThatExceptionOfType(CarException.class).isThrownBy(() -> carService.deleteAll());
    }

    @Test
    void showAllNotRentedCars() throws CarException{
        //given
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        when(carRepository.findAll()).thenReturn(cars);
        //when
        carService.showNotRented();
        //then
        assertThat(cars).isNotEmpty();
    }

    @Test
    void showAllNotRentedCarsExpection() {
        assertThatExceptionOfType(CarException.class).isThrownBy(() -> carService.showNotRented());
    }

    @Test
    void showAllRentedCars() throws CarException{
        //given
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        when(carRepository.findAll()).thenReturn(cars);
        //when
        carService.showRented();
        //then
        assertThat(cars).isNotEmpty();
    }

    @Test
    void showAllRentedCarsExpection() {
        assertThatExceptionOfType(CarException.class).isThrownBy(() -> carService.showRented());
    }

    @Test
    void showOlderCarsThan(){
        //given
        Date date1 = new Date(2000-01-01);
        List<Car> carListTest = carListTest();
        when(carRepository.findAll()).thenReturn(carListTest);
        //when
        List<Car> carList = carService.showOlderThan(date1);
        //then
        verify(carRepository,times(1)).findAll();
        assertThat(carListTest.size()).isGreaterThan(carList.size());
        assertThat(carList.size()==1);
    }


    @Test
    void showYoungerCarsThan(){
        //given
        Date date1 = new Date(2020-01-01);
        List<Car> carListTest = carListTest();
        when(carRepository.findAll()).thenReturn(carListTest);
        //when
        List<Car> carList = carService.showOlderThan(date1);
        //then
        assertThat(carList).isNotEmpty();
    }

    @Test
    void shouldFindAllCarsWithProvidedBrandAndModel() throws CarException{
        //given
        List<Car> carListTest = carListTest();
        when(carRepository.findAll()).thenReturn(carListTest);
        //when
        List<Car> carList = carService.findByBrandAndModel("bmw","a");
        //then
        assertThat(carList).isNotEmpty();
    }

    @Test
    void shouldFindAllCarsWithProvidedBrandAndModelException() {
        assertThatExceptionOfType(CarException.class).isThrownBy(() -> carService.findByBrandAndModel(car.getBrand(),car.getModel()));
    }

}

