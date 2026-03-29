package com.sameervhatkar.MovieBookingSystem.entity;

import com.sameervhatkar.MovieBookingSystem.enums.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat extends BaseModel {
    @ManyToOne
    private Auditorium auditorium;
    private String row_label;
    private Integer seat_number;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
}
