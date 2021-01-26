package com.pjatk.car_rental.service;

import com.pjatk.car_rental.model.Payment;
import com.pjatk.car_rental.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> findAll(){
        return paymentRepository.findAll();
    }

    public Payment savePayment(Payment payment){
        paymentRepository.save(payment);
        return payment;
    }

    public void deletePayment(Payment payment){
        paymentRepository.delete(payment);
    }
}
