package com.sameervhatkar.MovieBookingSystem.entity;

import com.sameervhatkar.MovieBookingSystem.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking extends BaseModel {

    @ManyToOne
    private User user;
    @ManyToOne
    private Show show;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    private Double totalAmount;

}