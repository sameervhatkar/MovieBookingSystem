package com.sameervhatkar.MovieBookingSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "booking_seats")
public class BookingSeat extends BaseModel {

    @ManyToOne
    private Booking booking;
    @ManyToOne
    private ShowSeat showSeat;
    private Double priceAtBooking;

}
