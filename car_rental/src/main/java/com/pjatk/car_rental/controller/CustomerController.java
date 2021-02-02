package com.pjatk.car_rental.controller;

import com.pjatk.car_rental.exception.CustomerExpection;
import com.pjatk.car_rental.model.Customer;
import com.pjatk.car_rental.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() throws CustomerExpection {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Customer>> findById(@PathVariable Long id) throws CustomerExpection {
        Optional<Customer> findById = customerService.findById(id);
        if (findById.isPresent()) {
            return ResponseEntity.ok(findById);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) throws CustomerExpection{
        return ResponseEntity.ok(customerService.saveCustomer(customer));
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteAll() throws CustomerExpection{
        customerService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws CustomerExpection{
        customerService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/more/{value}")
        public ResponseEntity<List<Customer>> showMoreRentsThan(@PathVariable int value) throws CustomerExpection{
            return ResponseEntity.ok(customerService.showMoreRentsThan(value));
        }

    @GetMapping("/less/{value}")
    public ResponseEntity<List<Customer>> showLessRentsThan(@PathVariable int value) throws CustomerExpection{
        return ResponseEntity.ok(customerService.showLessRentsThan(value));
    }

    @GetMapping("/equal/{value}")
    public ResponseEntity<List<Customer>> showRentsEqualsTo(@PathVariable int value) throws CustomerExpection{
        return ResponseEntity.ok(customerService.showRentsEqualsTo(value));
    }


}
