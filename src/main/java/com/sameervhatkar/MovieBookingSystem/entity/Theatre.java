package com.sameervhatkar.MovieBookingSystem.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Theatre extends BaseModel {
    private String name;
    private String address;
    private String city;
    private String state;
    private Integer pincode;
}
