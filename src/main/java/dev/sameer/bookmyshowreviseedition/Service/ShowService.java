package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.DTOs.ShowRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.ShowResponseDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.ShowUpdateResquestDTO;

import java.util.List;
import java.util.UUID;

public interface ShowService {
    ShowResponseDTO createShow(ShowRequestDTO showRequestDTO);

    ShowResponseDTO getShowById(UUID showId);
    List<ShowResponseDTO> getShowByTheatre(UUID theatreId);
    List<ShowResponseDTO> getShowByAudi(UUID audiId);

    ShowResponseDTO updateShow(UUID showid, ShowUpdateResquestDTO showUpdateResquestDTO);

    Boolean deleteShow(UUID showId);

}
