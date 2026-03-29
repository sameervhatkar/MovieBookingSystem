package com.sameervhatkar.MovieBookingSystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class TheatreResponseDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private int pincode;
    private Instant createdAt;
    private Instant updatedAt;
}
