package com.pjatk.car_rental.service;

import com.pjatk.car_rental.exception.CustomerExpection;
import com.pjatk.car_rental.model.Customer;
import com.pjatk.car_rental.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private Customer customer;

    @InjectMocks
    private CustomerService customerService;

    public List<Customer> customerListTest(){
        Customer customer1 = new Customer("a","b","111111",0);
        Customer customer2 = new Customer("c","b","222222",3);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        return customerList;
    }

    @Test
    void findAllCars() throws CustomerExpection {
        //given
        when(customerRepository.findAll()).thenReturn(List.of(new Customer()));
        //when
        List<Customer> all = customerRepository.findAll();
        //then
        assertThat(all).isNotEmpty();
    }

    @Test
    void shouldFindAllExpection(){
        assertThatExceptionOfType(CustomerExpection.class).isThrownBy(() -> customerService.findAll());
    }

    @Test
    void findCustomerById() throws CustomerExpection{
        //given
        when(customerRepository.findById(1L)).thenReturn(Optional.of(new Customer()));
        //when
        Optional<Customer> customer = customerService.findById(1L);
        //then
        assertThat(customer).isNotEmpty();
    }

    @Test
    void shouldFindByIdExpection(){
        assertThatExceptionOfType(CustomerExpection.class).isThrownBy(() -> customerService.findById(customer.getId()));
    }

    @Test
    void saveNewCustomer() throws CustomerExpection{
        //given
        Customer customer1 = new Customer("a","b","111111",0);
        Customer customer2 = new Customer(1L,"a","b","111111",0);
        when(customerRepository.save(customer1)).thenReturn(customer2);
        //when
        Customer newCustomer = customerService.saveCustomer(customer1);
        //then
        assertThat(newCustomer.getId()).isEqualTo(customer2.getId());
    }

    @Test
    void shouldDelete() throws CustomerExpection {
        //Given
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Optional<Customer> customer = customerService.findById(1L);
        //When
        customerService.deleteById(1L);
        //Then
        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldDeleteExpection(){
        assertThatExceptionOfType(CustomerExpection.class).isThrownBy(() -> customerService.deleteById(customer.getId()));
    }

    @Test
    void shouldDeleteAll() throws CustomerExpection {
        //given
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(customerRepository.findAll()).thenReturn(customers);
        //when
        customerService.deleteAll();
        //then
        verify(customerRepository,atLeastOnce()).deleteAll();
    }

    @Test
    void shouldDeleteAllExpection() {
        assertThatExceptionOfType(CustomerExpection.class).isThrownBy(() -> customerService.deleteAll());
    }

    @Test
    void showRentedMoreTimesThan() throws CustomerExpection{
        //given
        int value = 2;
        List<Customer> customerListTest = customerListTest();
        when(customerRepository.findAll()).thenReturn(customerListTest);
        //when
        List<Customer> customerList = customerService.showMoreRentsThan(value);
        //then
        assertThat(customerList).isNotEmpty();
    }

    @Test
    void showRentedMoreTimesThanException() {
        assertThatExceptionOfType(CustomerExpection.class).isThrownBy(() -> customerService.showMoreRentsThan(customer.getRents()));
    }

    @Test
    void showRentedLessTimesThan() throws CustomerExpection{
        //given
        int value = 0;
        List<Customer> customerListTest = customerListTest();
        when(customerRepository.findAll()).thenReturn(customerListTest);
        //when
        List<Customer> customerList = customerService.showMoreRentsThan(value);
        //then
        assertThat(customerList).isNotEmpty();
    }

    @Test
    void showRentedLessTimesThanException() {
        assertThatExceptionOfType(CustomerExpection.class).isThrownBy(() -> customerService.showMoreRentsThan(customer.getRents()));
    }

    @Test
    void showEqualValueOfTimes() throws CustomerExpection{
        //given
        int value = 0;
        List<Customer> customerListTest = customerListTest();
        when(customerRepository.findAll()).thenReturn(customerListTest);
        //when
        List<Customer> customerList = customerService.showMoreRentsThan(value);
        //then
        assertThat(customerList).isNotEmpty();
    }

    @Test
    void showEqualValueOfTimesException() {
        assertThatExceptionOfType(CustomerExpection.class).isThrownBy(() -> customerService.showMoreRentsThan(customer.getRents()));
    }
}
