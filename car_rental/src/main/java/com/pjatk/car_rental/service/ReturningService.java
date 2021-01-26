package com.pjatk.car_rental.service;

import com.pjatk.car_rental.model.Returning;
import com.pjatk.car_rental.repository.ReturningRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturningService {
    private ReturningRepository returningRepository;

    public ReturningService(ReturningRepository returningRepository) {
        this.returningRepository = returningRepository;
    }

    public List<Returning> findAll(){
        return returningRepository.findAll();
    }

    public Returning saveReturning(Returning returning){
        returningRepository.save(returning);
        return returning;
    }

    public void deleteReturning(Returning returning){
        returningRepository.delete(returning);
    }
}
