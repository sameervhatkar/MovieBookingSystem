package com.sameervhatkar.MovieBookingSystem.service;

import com.sameervhatkar.MovieBookingSystem.Mapper.EntityToDTOMapper;
import com.sameervhatkar.MovieBookingSystem.dto.AudiResponseDTO;
import com.sameervhatkar.MovieBookingSystem.dto.CreateAudiRequestDTO;
import com.sameervhatkar.MovieBookingSystem.dto.UpdateAuditoriumRequestDTO;
import com.sameervhatkar.MovieBookingSystem.entity.Auditorium;
import com.sameervhatkar.MovieBookingSystem.entity.Theatre;
import com.sameervhatkar.MovieBookingSystem.exceptions.TheatreNotFoundException;
import com.sameervhatkar.MovieBookingSystem.exceptions.AuditoriumNotFoundException;
import com.sameervhatkar.MovieBookingSystem.repository.AuditoriumRepository;
import com.sameervhatkar.MovieBookingSystem.repository.TheatreRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditoriumService {

    private final AuditoriumRepository auditoriumRepository;
    private final TheatreRepository theatreRepository;

    public AuditoriumService(AuditoriumRepository auditoriumRepository, TheatreRepository theatreRepository) {
        this.auditoriumRepository = auditoriumRepository;
        this.theatreRepository = theatreRepository;
    }

    //CRUD

    public AudiResponseDTO createAuditorium(CreateAudiRequestDTO requestDTO) {
        Auditorium audi = new Auditorium();
        audi.setName(requestDTO.getName());
        Theatre theatre = theatreRepository.findById(requestDTO.getTheatreId()).orElseThrow(
                () -> new TheatreNotFoundException("Theatre is not found with this id.")
        );
        audi.setTheatre(theatre);
        audi.setScreenType(requestDTO.getScreenType());
        Auditorium savedAudi = auditoriumRepository.save(audi);

        return EntityToDTOMapper.convertAudiEntityToDTO(savedAudi);
    }

    public AudiResponseDTO getAuditoriumById(Long id) {
        Auditorium audi = auditoriumRepository.findById(id).orElseThrow(
                () -> new AuditoriumNotFoundException("Auditorium is not found with this id.")
        );

        return EntityToDTOMapper.convertAudiEntityToDTO(audi);
    }

    public AudiResponseDTO updateAuditorium(UpdateAuditoriumRequestDTO requestDTO) {
        Auditorium audi = auditoriumRepository.findById(requestDTO.getId()).orElseThrow(
                () -> new AuditoriumNotFoundException("Auditorium is not found with this id.")
        );
        audi.setName(requestDTO.getName());
        Theatre theatre = theatreRepository.findById(requestDTO.getTheatreId()).orElseThrow(
                () -> new TheatreNotFoundException("Theatre is not found with this id.")
        );
        audi.setTheatre(theatre);
        audi.setScreenType(requestDTO.getScreenType());
        Auditorium savedAudi = auditoriumRepository.save(audi);

        return EntityToDTOMapper.convertAudiEntityToDTO(savedAudi);

    }

    public void deleteAuditorium(Long id) {
        auditoriumRepository.deleteById(id);
    }
}
