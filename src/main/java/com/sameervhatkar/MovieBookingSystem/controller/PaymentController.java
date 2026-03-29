package com.sameervhatkar.MovieBookingSystem.controller;

import com.sameervhatkar.MovieBookingSystem.dto.CreatePaymentRequestDTO;
import com.sameervhatkar.MovieBookingSystem.dto.PaymentResponseDTO;
import com.sameervhatkar.MovieBookingSystem.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payments")
    public ResponseEntity<PaymentResponseDTO> createPayment(@Valid @RequestBody CreatePaymentRequestDTO requestDTO) {
        PaymentResponseDTO responseDTO = paymentService.createPayment(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}