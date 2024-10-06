package dev.sameer.bookmyshowreviseedition.DTOs;

import dev.sameer.bookmyshowreviseedition.Entity.Theatre;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CityResponseDTO {
    private String cityName;
    private List<String> theatreName;
}
