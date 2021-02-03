package com.pjatk.car_rental.service;

import com.pjatk.car_rental.exception.CustomerExpection;
import com.pjatk.car_rental.model.Customer;
import com.pjatk.car_rental.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() throws CustomerExpection {
        if (customerRepository.findAll().size() > 0){
            return customerRepository.findAll();
        } else{
            throw new CustomerExpection();
        }
    }

    public Optional<Customer> findById(Long id) throws CustomerExpection {
        if (customerRepository.findById(id).isPresent()){
            return customerRepository.findById(id);
        }
        else {
            throw new CustomerExpection();
        }
    }

    public Customer saveCustomer(Customer customer){
            return customerRepository.save(customer);
    }

    public void deleteAll() throws CustomerExpection{
        if (customerRepository.findAll().size() > 0){
            customerRepository.deleteAll();
        } else{
            throw new CustomerExpection();
        }
    }

    public void deleteById(Long id) throws CustomerExpection{
        if (customerRepository.findById(id).isPresent()){
            customerRepository.deleteById(id);
        }
        else {
            throw new CustomerExpection();
        }
    }

    public List<Customer> showMoreRentsThan(int value) throws CustomerExpection{
        List<Customer> customers = customerRepository.findAll();
        if(customers.size() > 0) {
            return customers.stream().filter(car -> car.getRents() > value).collect(Collectors.toList());
        }
        else{
            throw new CustomerExpection();
        }
    }

    public List<Customer> showLessRentsThan(int value) throws CustomerExpection{
        List<Customer> customers = customerRepository.findAll();
        if(customers.size() > 0) {
            return customers.stream().filter(car -> car.getRents() < value).collect(Collectors.toList());
        }
        else{
            throw new CustomerExpection();
        }
    }

    public List<Customer> showRentsEqualsTo(int value) throws CustomerExpection{
        List<Customer> customers = customerRepository.findAll();
        if(customers.size() > 0) {
            return customers.stream().filter(car -> car.getRents() == value).collect(Collectors.toList());
        }
        else{
            throw new CustomerExpection();
        }
    }
}
