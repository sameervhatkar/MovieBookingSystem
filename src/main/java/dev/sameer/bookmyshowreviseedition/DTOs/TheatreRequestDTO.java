package dev.sameer.bookmyshowreviseedition.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TheatreRequestDTO {
    private UUID cityId;
    private String theatreName;
    private String theatreAddress;
}
