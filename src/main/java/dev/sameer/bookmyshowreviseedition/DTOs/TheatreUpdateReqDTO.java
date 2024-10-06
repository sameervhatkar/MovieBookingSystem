package dev.sameer.bookmyshowreviseedition.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TheatreUpdateReqDTO {
    private UUID theatreId;
    private String theatreName;
}
