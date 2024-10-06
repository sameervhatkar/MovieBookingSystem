package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.DTOs.ShowRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.ShowResponseDTO;

public interface ShowService {
    ShowResponseDTO createShow(ShowRequestDTO showRequestDTO);
}
