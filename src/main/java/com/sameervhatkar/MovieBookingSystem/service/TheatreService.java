package com.sameervhatkar.MovieBookingSystem.service;

import com.sameervhatkar.MovieBookingSystem.Mapper.EntityToDTOMapper;
import com.sameervhatkar.MovieBookingSystem.dto.CreateTheatreRequestDTO;
import com.sameervhatkar.MovieBookingSystem.dto.TheatreResponseDTO;
import com.sameervhatkar.MovieBookingSystem.dto.UpdateTheatreRequestDTO;
import com.sameervhatkar.MovieBookingSystem.entity.Theatre;
import com.sameervhatkar.MovieBookingSystem.exceptions.TheatreNotFoundException;
import com.sameervhatkar.MovieBookingSystem.repository.TheatreRepository;
import org.springframework.stereotype.Service;

@Service
public class TheatreService {

    private TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    //CRUD

    public TheatreResponseDTO createTheatre(CreateTheatreRequestDTO requestDTO) {
        Theatre theatre = new Theatre();
        theatre.setName(requestDTO.getName());
        theatre.setAddress(requestDTO.getAddress());
        theatre.setCity(requestDTO.getCity());
        theatre.setState(requestDTO.getState());
        theatre.setPincode(requestDTO.getPincode());

        Theatre savedTheatre = theatreRepository.save(theatre);
        return EntityToDTOMapper.convertTheatreEntityToDTO(savedTheatre);
    }

    public TheatreResponseDTO getTheatreById(Long id) {
        Theatre theatre = theatreRepository.findById(id).orElseThrow(
                () -> new TheatreNotFoundException("Theatre with this id is not found.")
        );

        return EntityToDTOMapper.convertTheatreEntityToDTO(theatre);
    }

    public TheatreResponseDTO updateTheatre(UpdateTheatreRequestDTO requestDTO) {
        Theatre theatre = theatreRepository.findById(requestDTO.getId()).orElseThrow(
                () -> new TheatreNotFoundException("Theatre with this id is not found.")
        );

        theatre.setName(requestDTO.getName());
        theatre.setAddress(requestDTO.getAddress());
        theatre.setCity(requestDTO.getCity());
        theatre.setState(requestDTO.getState());
        theatre.setPincode(requestDTO.getPincode());

        Theatre updatedTheatre = theatreRepository.save(theatre);

        return EntityToDTOMapper.convertTheatreEntityToDTO(updatedTheatre);
    }

    public void deleteTheatre(Long id) {
        theatreRepository.deleteById(id);
    }
}
