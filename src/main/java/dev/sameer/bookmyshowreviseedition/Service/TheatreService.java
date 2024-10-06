package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.DTOs.TheatreRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.TheatreResponseDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.TheatreUpdateReqDTO;
import dev.sameer.bookmyshowreviseedition.Entity.Movie;
import dev.sameer.bookmyshowreviseedition.Entity.Theatre;

import java.util.List;
import java.util.UUID;

public interface TheatreService {
    TheatreResponseDTO createTheatre(TheatreRequestDTO theatreRequestDTO);
    TheatreResponseDTO getTheatre(UUID theatreId);
    List<TheatreResponseDTO> getAllTheatres();
    TheatreResponseDTO updateTheatre(TheatreUpdateReqDTO theatreUpdateReqDTO);
    boolean deleteTheatre(UUID theatreId);

    Theatre getTheatreById(UUID theatreId);
    List<Theatre> getAllTheatre();
    void saveTheatre(Theatre theatre);
    void addMovie(UUID theatreId, UUID movieId);


}
