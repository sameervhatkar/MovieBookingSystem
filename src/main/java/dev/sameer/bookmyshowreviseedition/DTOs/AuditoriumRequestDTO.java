package dev.sameer.bookmyshowreviseedition.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class AuditoriumRequestDTO {
    private UUID theatreId;
    private String audiName;
    private int capacity;
}
