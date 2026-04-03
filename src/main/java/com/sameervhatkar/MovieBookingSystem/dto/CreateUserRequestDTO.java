package com.sameervhatkar.MovieBookingSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotNull
    private String password;
    @NotBlank
    private String phoneNumber;

}
