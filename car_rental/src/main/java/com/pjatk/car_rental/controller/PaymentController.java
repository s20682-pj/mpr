package com.pjatk.car_rental.controller;

import com.pjatk.car_rental.model.Payment;
import com.pjatk.car_rental.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> findAll(){
        return ResponseEntity.ok(paymentService.findAll());
    }

    @PostMapping
    public ResponseEntity<Payment> savePayment(@RequestBody Payment payment){
        return ResponseEntity.ok(paymentService.savePayment(payment));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePayment(@RequestBody Payment payment){
        paymentService.deletePayment(payment);
        return ResponseEntity.ok().build();
    }
}
