package com.sameervhatkar.MovieBookingSystem.repository;

import com.sameervhatkar.MovieBookingSystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
