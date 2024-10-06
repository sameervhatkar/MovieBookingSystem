package dev.sameer.bookmyshowreviseedition.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TheatreResponseDTO {
    private String theatreName;
    private String theatreAddress;
    private List<String> moviesName;
}
