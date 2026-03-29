package com.sameervhatkar.MovieBookingSystem.dto;

import com.sameervhatkar.MovieBookingSystem.enums.ScreenType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAudiRequestDTO {
    @NotBlank
    private String name;
    @NotNull
    private Long theatreId;
    @NotNull
    private ScreenType screenType;
}
