package dev.sameer.bookmyshowreviseedition.Service;


import dev.sameer.bookmyshowreviseedition.DTOs.AuditoriumRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.AuditoriumResponseDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.AuditoriumUpdateRequestDTO;
import dev.sameer.bookmyshowreviseedition.Entity.Auditorium;

import java.util.List;
import java.util.UUID;

public interface AuditoriumService {
    AuditoriumResponseDTO createAuditorium(AuditoriumRequestDTO auditoriumRequestDTO);

    AuditoriumResponseDTO getAudi(UUID audiId);
    List<AuditoriumResponseDTO> getAudisofTheatre(UUID theatreId);
    List<AuditoriumResponseDTO> getAllAudis();

    AuditoriumResponseDTO updateAudi(AuditoriumUpdateRequestDTO auditoriumUpdateRequestDTO);

    Boolean deleteAudi(UUID audiId);

    Auditorium getAudiById(UUID audiId);

    void saveAudi(Auditorium auditorium);
}
