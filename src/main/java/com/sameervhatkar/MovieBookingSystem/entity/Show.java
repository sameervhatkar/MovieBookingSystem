package com.sameervhatkar.MovieBookingSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "shows")
public class Show extends BaseModel {
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Auditorium auditorium;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double basePrice;
}
