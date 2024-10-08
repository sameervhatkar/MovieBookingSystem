package dev.sameer.bookmyshowreviseedition.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ShowResponseDTO {
    private UUID theatreid;
    private UUID audiId;
    private UUID  movieId;
    private LocalDateTime showTime;
}
