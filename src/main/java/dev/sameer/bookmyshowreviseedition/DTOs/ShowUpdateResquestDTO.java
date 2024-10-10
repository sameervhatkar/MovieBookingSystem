package dev.sameer.bookmyshowreviseedition.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ShowUpdateResquestDTO {
    private UUID theatreId;
    private UUID audiId;
    private UUID movieId;
    private LocalDateTime showTiming;
}
