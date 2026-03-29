package com.sameervhatkar.MovieBookingSystem.dto;

import com.sameervhatkar.MovieBookingSystem.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Getter
@Setter
public class PaymentResponseDTO {
    private Long id;
    private Long bookingId;
    private Double amount;
    private PaymentStatus status;
    private String paymentGatewayReferenceId;
    private Instant createdAt;
    private Instant updatedAt;
}