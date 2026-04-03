package com.sameervhatkar.MovieBookingSystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequestDTO {
    @NotNull
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
}