package com.sameervhatkar.MovieBookingSystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTheatreRequestDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private Integer pincode;
}
