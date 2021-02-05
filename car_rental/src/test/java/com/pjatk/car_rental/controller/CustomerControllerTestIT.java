package com.pjatk.car_rental.controller;

import com.pjatk.car_rental.model.Car;
import com.pjatk.car_rental.model.Customer;
import com.pjatk.car_rental.repository.CustomerRepository;
import com.pjatk.car_rental.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class CustomerControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;



    @Test
    void shouldFindAll() throws Exception{
        Customer customer1 = new Customer(1L,"a","b","111111",0);
        Customer customer2 = new Customer(2L,"c","b","222222",3);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);


        when(customerRepository.findAll()).thenReturn(customerList);
        mockMvc.perform(get("/customer"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"a\",\"surname\":\"b\",\"phone_number\":\"111111\",\"rents\":0},{\"id\":2,\"name\":\"c\",\"surname\":\"b\",\"phone_number\":\"222222\",\"rents\":3}]")));
    }


    @Test
    void shouldDeleteById() throws Exception{
        Optional<Customer> customer = Optional.of(new Customer(1L,"a","b","111",0));

        when(customerRepository.findById(customer.get().getId())).thenReturn(customer);
        mockMvc.perform(delete("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));
    }


    @Test
    void shouldDeleteAll() throws Exception{
        Customer customer1 = new Customer("a","b","111111",0);
        Customer customer2 = new Customer("c","b","222222",3);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);


        when(customerRepository.findAll()).thenReturn(customerList);
        mockMvc.perform(delete("/customer"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));
    }

    @Test
    void shouldFindCustomerById() throws Exception{
        Optional<Customer> customer1 = Optional.of(new Customer(1L,"a", "b", "111111", 0));

        when(customerRepository.findById(customer1.get().getId())).thenReturn(customer1);
        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"a\",\"surname\":\"b\",\"phone_number\":\"111111\",\"rents\":0}")));
    }


    @Test
    void shouldShowMoreThanXRents() throws Exception{
        Customer customer1 = new Customer(1L,"a","b","111111",0);
        Customer customer2 = new Customer(2L,"c","b","222222",3);
        customerService.saveCustomer(customer1);
        customerService.saveCustomer(customer2);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);

        when(customerRepository.findAll()).thenReturn(customerList);
        mockMvc.perform(get("/more/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":2,\"name\":\"c\",\"surname\":\"b\",\"phone_number\":\"222222\",\"rents\":3}")));
    }

    @Test
    void shouldShowLessThanXRents() throws Exception{
        Customer customer1 = new Customer(1L,"a","b","111111",0);
        Customer customer2 = new Customer(2L,"c","b","222222",3);
        customerService.saveCustomer(customer1);
        customerService.saveCustomer(customer2);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);

        when(customerRepository.findAll()).thenReturn(customerList);
        mockMvc.perform(get("/less/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"a\",\"surname\":\"b\",\"phone_number\":\"111111\",\"rents\":0}")));
    }

    @Test
    void shouldShowExactlyXRents() throws Exception{
        Customer customer1 = new Customer(1L,"a","b","111111",0);
        Customer customer2 = new Customer(2L,"c","b","222222",3);
        customerService.saveCustomer(customer1);
        customerService.saveCustomer(customer2);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);

        when(customerRepository.findAll()).thenReturn(customerList);
        mockMvc.perform(get("/equal/0"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"a\",\"surname\":\"b\",\"phone_number\":\"111111\",\"rents\":0}")));
    }
}
