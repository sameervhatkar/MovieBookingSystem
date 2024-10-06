package dev.sameer.bookmyshowreviseedition.DTOs;

import dev.sameer.bookmyshowreviseedition.Enum.SeatStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatResponseDTO {
    private String theatreName;
    private String audiName;
    private String seatName;
    private SeatStatus seatStatus;
}
