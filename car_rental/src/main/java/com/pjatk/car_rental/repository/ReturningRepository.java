package com.pjatk.car_rental.repository;

import com.pjatk.car_rental.model.Returning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturningRepository extends JpaRepository<Returning, Long> {
}
