package com.pjatk.car_rental.service;

import com.pjatk.car_rental.model.Customer;
import com.pjatk.car_rental.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        customerRepository.save(customer);
        return customer;
    }

    public void deleteCustomer(Customer customer){
        customerRepository.delete(customer);
    }
}
