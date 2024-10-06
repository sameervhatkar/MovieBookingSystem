package dev.sameer.bookmyshowreviseedition.Service;


import dev.sameer.bookmyshowreviseedition.DTOs.AuditoriumRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.AuditoriumResponseDTO;

public interface AuditoriumService {
    AuditoriumResponseDTO createAuditorium(AuditoriumRequestDTO auditoriumRequestDTO);
}
