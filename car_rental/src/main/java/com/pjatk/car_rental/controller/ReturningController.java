package com.pjatk.car_rental.controller;

import com.pjatk.car_rental.model.Returning;
import com.pjatk.car_rental.service.ReturningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/returning")
public class ReturningController {
    private ReturningService returningService;

    public ReturningController(ReturningService returningService) {
        this.returningService = returningService;
    }

    @GetMapping
    public ResponseEntity<List<Returning>> findAll(){
        return ResponseEntity.ok(returningService.findAll());
    }

    @PostMapping
    public ResponseEntity<Returning> saveReturning(@RequestBody Returning returning){
        return ResponseEntity.ok(returningService.saveReturning(returning));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteReturning(@RequestBody Returning returning){
        returningService.deleteReturning(returning);
        return ResponseEntity.ok().build();
    }
}
