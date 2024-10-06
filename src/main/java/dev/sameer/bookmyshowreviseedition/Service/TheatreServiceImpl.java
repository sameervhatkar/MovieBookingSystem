package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.DTOs.TheatreRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.TheatreResponseDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.TheatreUpdateReqDTO;
import dev.sameer.bookmyshowreviseedition.Entity.Auditorium;
import dev.sameer.bookmyshowreviseedition.Entity.City;
import dev.sameer.bookmyshowreviseedition.Entity.Movie;
import dev.sameer.bookmyshowreviseedition.Entity.Theatre;
import dev.sameer.bookmyshowreviseedition.Mapper.EntityToDTOMapper;
import dev.sameer.bookmyshowreviseedition.Repo.CityRepo;
import dev.sameer.bookmyshowreviseedition.Repo.TheatreRepo;
import dev.sameer.bookmyshowreviseedition.Exceptions.TheatreNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    private TheatreRepo theatreRepo;

    @Autowired
    private CityService cityService;

    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private MovieService movieService;

    @Override
    public TheatreResponseDTO createTheatre(TheatreRequestDTO theatreRequestDTO) {
        City city = cityService.getCityByCityId(theatreRequestDTO.getCityId());
        Theatre theatre = new Theatre();
        theatre.setTheatreName(theatreRequestDTO.getTheatreName());
        theatre.setTheatreAddress(theatreRequestDTO.getTheatreAddress());
        theatreRepo.save(theatre);
        List<Theatre> theatres = new ArrayList<>(city.getTheatreList());
        theatres.add(theatre);
        city.setTheatreList(theatres);
        cityRepo.save(city);
        return EntityToDTOMapper.convertTheatreEntitytoDTO(theatre);
    }

    @Override
    public TheatreResponseDTO getTheatre(UUID theatreId) {
        Theatre theatre = theatreRepo.getReferenceById(theatreId);
        return EntityToDTOMapper.convertTheatreEntitytoDTO(theatre);
    }

    @Override
    public List<TheatreResponseDTO> getAllTheatres() {
        List<Theatre> theatres = theatreRepo.findAll();
        List<TheatreResponseDTO> theatreResponseDTOS = new ArrayList<>();
        for(Theatre theatre : theatres) {
            TheatreResponseDTO theatreResponseDTO = EntityToDTOMapper.convertTheatreEntitytoDTO(theatre);
            theatreResponseDTOS.add(theatreResponseDTO);
        }
        return theatreResponseDTOS;
    }

    @Override
    public TheatreResponseDTO updateTheatre(TheatreUpdateReqDTO theatreUpdateReqDTO) {
        Theatre theatre = theatreRepo.getReferenceById(theatreUpdateReqDTO.getTheatreId());
        theatre.setTheatreName(theatreUpdateReqDTO.getTheatreName());
        theatreRepo.save(theatre);
        return EntityToDTOMapper.convertTheatreEntitytoDTO(theatre);
    }

    @Override
    public boolean deleteTheatre(UUID theatreId) {
        Theatre theatre = theatreRepo.getReferenceById(theatreId);
        theatreRepo.delete(theatre);
        return true;
    }

    @Override
    public Theatre getTheatreById(UUID theatreId) {
        return theatreRepo.findById(theatreId).orElseThrow(
                () -> new TheatreNotFoundException("Theatre Not found.")
        );
    }

    @Override
    public List<Theatre> getAllTheatre() {
        return theatreRepo.findAll();
    }

    @Override
    public void saveTheatre(Theatre theatre) {
        theatreRepo.save(theatre);
    }

    @Override
    public void addMovie(UUID theatreId, UUID movieId) {
        Theatre theatre = theatreRepo.findById(theatreId).orElseThrow(
                () -> new TheatreNotFoundException("Theatre Not found")
        );
        Movie movie = movieService.getMovie(movieId);
        theatre.setMovieList(List.of(movie));
        theatreRepo.save(theatre);
    }
}
