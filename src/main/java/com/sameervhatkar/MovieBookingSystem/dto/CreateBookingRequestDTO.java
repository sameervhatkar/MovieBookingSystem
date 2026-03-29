package com.sameervhatkar.MovieBookingSystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CreateBookingRequestDTO {

    @NotNull
    private Long userId;
    @NotNull
    private Long showId;
    @NotNull
    private List<Long> showSeatIds;
}