package com.sameervhatkar.MovieBookingSystem.dto;

import com.sameervhatkar.MovieBookingSystem.enums.ScreenType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAuditoriumRequestDTO {
    @NotNull
    private Long id;
    private String name;
    private Long theatreId;
    private ScreenType screenType;
}
