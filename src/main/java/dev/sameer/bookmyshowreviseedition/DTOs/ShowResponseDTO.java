package dev.sameer.bookmyshowreviseedition.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ShowResponseDTO {
    private String theatreName;
    private String audiName;
    private String movieName;
    private LocalDateTime showTime;
}
