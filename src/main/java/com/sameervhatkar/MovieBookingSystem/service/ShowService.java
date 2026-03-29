package com.sameervhatkar.MovieBookingSystem.service;

import com.sameervhatkar.MovieBookingSystem.Mapper.EntityToDTOMapper;
import com.sameervhatkar.MovieBookingSystem.dto.CreateShowRequestDTO;
import com.sameervhatkar.MovieBookingSystem.dto.ShowResponseDTO;
import com.sameervhatkar.MovieBookingSystem.dto.UpdateShowRequestDTO;
import com.sameervhatkar.MovieBookingSystem.entity.Auditorium;
import com.sameervhatkar.MovieBookingSystem.entity.Movie;
import com.sameervhatkar.MovieBookingSystem.entity.Seat;
import com.sameervhatkar.MovieBookingSystem.entity.Show;
import com.sameervhatkar.MovieBookingSystem.exceptions.AuditoriumNotFoundException;
import com.sameervhatkar.MovieBookingSystem.exceptions.MovieNotFoundException;
import com.sameervhatkar.MovieBookingSystem.exceptions.ShowNotFoundException;
import com.sameervhatkar.MovieBookingSystem.repository.AuditoriumRepository;
import com.sameervhatkar.MovieBookingSystem.repository.MovieRepository;
import com.sameervhatkar.MovieBookingSystem.repository.SeatRepository;
import com.sameervhatkar.MovieBookingSystem.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final AuditoriumRepository auditoriumRepository;
    private final SeatRepository seatRepository;
    private final ShowSeatService showSeatService;

    public ShowService(ShowRepository showRepository,
                       MovieRepository movieRepository,
                       AuditoriumRepository auditoriumRepository,
                       SeatRepository seatRepository,
                       ShowSeatService showSeatService) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.auditoriumRepository = auditoriumRepository;
        this.seatRepository = seatRepository;
        this.showSeatService = showSeatService;
    }

    public ShowResponseDTO createShow(CreateShowRequestDTO requestDTO) {

        Movie movie = movieRepository.findById(requestDTO.getMovieId())
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));

        Auditorium auditorium = auditoriumRepository.findById(requestDTO.getAuditoriumId())
                .orElseThrow(() -> new AuditoriumNotFoundException("Auditorium not found"));

        Show show = new Show();
        show.setMovie(movie);
        show.setAuditorium(auditorium);
        show.setStartTime(requestDTO.getStartTime());
        show.setEndTime(requestDTO.getEndTime());
        show.setBasePrice(requestDTO.getBasePrice());

        Show savedShow = showRepository.save(show);

        List<Seat> seats = seatRepository.findByAuditoriumId(auditorium.getId());

        showSeatService.generateShowSeatsForShow(savedShow, seats);

        return EntityToDTOMapper.convertShowEntityToDTO(savedShow);
    }

    public ShowResponseDTO getShowById(Long id) {

        Show show = showRepository.findById(id)
                .orElseThrow(() -> new ShowNotFoundException("Show not found"));

        return EntityToDTOMapper.convertShowEntityToDTO(show);
    }

    public ShowResponseDTO updateShow(UpdateShowRequestDTO requestDTO) {

        Show show = showRepository.findById(requestDTO.getId())
                .orElseThrow(() -> new ShowNotFoundException("Show not found"));

        Movie movie = movieRepository.findById(requestDTO.getMovieId())
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));

        Auditorium auditorium = auditoriumRepository.findById(requestDTO.getAuditoriumId())
                .orElseThrow(() -> new AuditoriumNotFoundException("Auditorium not found"));

        show.setMovie(movie);
        show.setAuditorium(auditorium);
        show.setStartTime(requestDTO.getStartTime());
        show.setEndTime(requestDTO.getEndTime());
        show.setBasePrice(requestDTO.getBasePrice());

        Show savedShow = showRepository.save(show);

        return EntityToDTOMapper.convertShowEntityToDTO(savedShow);
    }

    public void deleteShow(Long id) {

        Show show = showRepository.findById(id)
                .orElseThrow(() -> new ShowNotFoundException("Show not found"));

        showRepository.delete(show);
    }
}