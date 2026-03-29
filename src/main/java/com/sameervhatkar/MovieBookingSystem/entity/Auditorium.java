package com.sameervhatkar.MovieBookingSystem.entity;

import com.sameervhatkar.MovieBookingSystem.enums.ScreenType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Auditorium extends BaseModel {
    private String name;
    @ManyToOne
    private Theatre theatre;
    @Enumerated(EnumType.STRING)
    private ScreenType screenType;

}
