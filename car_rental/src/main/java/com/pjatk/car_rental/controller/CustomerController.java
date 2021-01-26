package com.pjatk.car_rental.controller;

import com.pjatk.car_rental.model.Customer;
import com.pjatk.car_rental.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.saveCustomer(customer));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCustomer(@RequestBody Customer customer){
        customerService.deleteCustomer(customer);
        return ResponseEntity.ok().build();
    }
}
