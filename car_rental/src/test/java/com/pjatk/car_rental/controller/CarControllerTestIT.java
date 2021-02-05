package com.pjatk.car_rental.controller;

import com.pjatk.car_rental.model.Car;
import com.pjatk.car_rental.repository.CarRepository;
import com.pjatk.car_rental.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Test
    void shouldFindAll() throws Exception{
        Date date1 = new Date(2020-01-01);
        Car car = new Car("bmw","a","aaa",date1,0);
        List<Car> carList = new ArrayList<>();
        carList.add(car);

        when(carRepository.findAll()).thenReturn(carList);
        mockMvc.perform(get("/car/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"brand\":\"mini\",\"model\":\"1\",\"plate\":\"a\",\"manufacture_date\":\"2020-01-01\",\"rented\":0}")));
    }

    @Test
    void shouldDeleteById() throws Exception{
        Date date1 = new Date(2020-01-01);
        Optional<Car> car = Optional.of(new Car(1L,"mini","1","a",date1,0));

        when(carRepository.findById(car.get().getId())).thenReturn(car);
        mockMvc.perform(delete("/car/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));
    }

    @Test
    void shouldDeleteAll() throws Exception{
        Date date1 = new Date(2020-01-01);
        Date date2 = new Date(2000-01-01);
        Car car1 = new Car("bmw","a","aaa",date1,0);
        Car car2 = new Car(1L,"bmw","a","aaa",date2,0);
        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);


        when(carRepository.findAll()).thenReturn(carList);
        mockMvc.perform(delete("/car"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));
    }

    @Test
    void shouldFindCarById() throws Exception{
        Date date1 = new Date(2020-01-01);
        Optional<Car> car = Optional.of(new Car(1L,"mini","1","a",date1,0));

        when(carRepository.findById(car.get().getId())).thenReturn(car);
        mockMvc.perform(get("/car/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"brand\":\"mini\",\"model\":\"1\",\"plate\":\"a\",\"manufacture_date\":\"2020-01-01\",\"rented\":0}")));
    }


}
