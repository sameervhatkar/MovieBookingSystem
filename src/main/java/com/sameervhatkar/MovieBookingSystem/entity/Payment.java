package com.sameervhatkar.MovieBookingSystem.entity;

import com.sameervhatkar.MovieBookingSystem.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment extends BaseModel {
    @ManyToOne
    private Booking booking;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private String paymentGatewayReferenceId;
}
