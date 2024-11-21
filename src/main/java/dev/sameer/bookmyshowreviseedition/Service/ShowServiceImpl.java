package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.DTOs.ShowRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.ShowResponseDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.ShowUpdateResquestDTO;
import dev.sameer.bookmyshowreviseedition.Entity.*;
import dev.sameer.bookmyshowreviseedition.Exceptions.*;
import dev.sameer.bookmyshowreviseedition.Mapper.EntityToDTOMapper;
import dev.sameer.bookmyshowreviseedition.Repo.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShowServiceImpl implements ShowService{

    @Autowired
    private ShowRepo showRepo;

    @Autowired
    private TheatreService theatreService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ShowSeatService showSeatService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Override
    public ShowResponseDTO createShow(ShowRequestDTO showRequestDTO) {
        /*
            ShowRequestDTO
            private UUID theatreId;
            private UUID audiId;
            private UUID movieId;
            private LocalDateTime showTiming;
         */
        Show show = new Show();
        show.setTheaterId(showRequestDTO.getTheatreId());
        Theatre theatre = theatreService.getTheatreById(showRequestDTO.getTheatreId());
        List<Auditorium> auditoriumList = theatre.getAuditoriumList();
        Auditorium actualAudi = null;
        boolean flag = false;
        for(Auditorium auditorium : auditoriumList) {
            if(auditorium.getId().equals(showRequestDTO.getAudiId())) {
                actualAudi = auditorium;
                flag = true;
                break;
            }
        }
        if(!flag) throw new AuditoriumNotFoundException("Audi with audi Id " + showRequestDTO.getAudiId() + " not found");

        Movie movie = movieService.getMovie(showRequestDTO.getMovieId());


        List<Show> actualDateShow = new ArrayList<>();
        LocalDate targetDate = showRequestDTO.getShowTiming().toLocalDate();
        for(Show show1 : actualAudi.getShows()) {
            LocalDate date = show1.getShowTimings().toLocalDate();
            if(date.equals(targetDate))
                actualDateShow.add(show1);
        }

        LocalDateTime newShowStartTime = showRequestDTO.getShowTiming();
        LocalDateTime newShowEndTime = newShowStartTime.plusMinutes(movieService.duration(movie.getId()));

        for(Show existingShow : actualDateShow) {
            LocalDateTime showStartTime = existingShow.getShowTimings();
            int existingShowDuration = movieService.duration(existingShow.getMovieId());
            LocalDateTime showEndTime = showStartTime.plusMinutes(existingShowDuration);

            if(newShowStartTime.isBefore(showEndTime) && newShowEndTime.isAfter(showStartTime))
                throw new ShowTimingConflictException("New show timing conflicts with existing show in the same auditorium.");
        }

        show.setAudiId(actualAudi.getId());
        show.setMovieId(movie.getId());
        show.setShowTimings(newShowStartTime);
        showRepo.save(show);

        if (actualAudi.getShows() == null) {
            actualAudi.setShows(new ArrayList<>());
        }
        actualAudi.getShows().add(show);
        auditoriumService.saveAudi(actualAudi);

        List<ShowSeat> showSeats = new ArrayList<>();
        for(Seat seat : actualAudi.getSeatList()) {
            showSeats.add(showSeatService.generateShowSeat(show, seat));
        }
        show.setShowSeats(showSeats);
        return EntityToDTOMapper.convertShowEntitytoDTO(show);
    }

    @Override
    public ShowResponseDTO getShowById(UUID showId) {
        Show show = showRepo.findById(showId).orElseThrow(
                () -> new ShowNotFoundException("Show not found")
        );
        return EntityToDTOMapper.convertShowEntitytoDTO(show);
    }

    @Override
    public List<ShowResponseDTO> getShowByTheatre(UUID theatreId) {
        Theatre theatre = theatreService.getTheatreById(theatreId);
        List<Show> shows = new ArrayList<>();
        for(Auditorium auditorium : theatre.getAuditoriumList()) {
            shows.addAll(auditorium.getShows());
        }
        List<ShowResponseDTO> showResponseDTOS = new ArrayList<>();
        for(Show show : shows)
            showResponseDTOS.add(EntityToDTOMapper.convertShowEntitytoDTO(show));
        return showResponseDTOS;
    }

    @Override
    public List<ShowResponseDTO> getShowByAudi(UUID audiId) {
        Auditorium auditorium = auditoriumService.getAudiById(audiId);
        List<Show> shows = new ArrayList<>(auditorium.getShows());
        List<ShowResponseDTO> showResponseDTOs = new ArrayList<>();
        for(Show show : shows)
            showResponseDTOs.add(EntityToDTOMapper.convertShowEntitytoDTO(show));
        return showResponseDTOs;
    }

    @Override
    public ShowResponseDTO updateShow(UUID showId, ShowUpdateResquestDTO showUpdateResquestDTO) {
        Show show = showRepo.findById(showId).orElseThrow(
                () -> new ShowNotFoundException("Show with ID " + showId + " not found")
        );

        Theatre theatre = theatreService.getTheatreById(showUpdateResquestDTO.getTheatreId());

        Auditorium auditorium = auditoriumService.getAudiById(showUpdateResquestDTO.getAudiId());

        Movie movie = movieService.getMovie(showUpdateResquestDTO.getMovieId());

        List<Show> actualDateShow = new ArrayList<>();
        LocalDate targetDate = showUpdateResquestDTO.getShowTiming().toLocalDate();
        for (Show existingShow : auditorium.getShows()) {
            LocalDate date = existingShow.getShowTimings().toLocalDate();
            if (date.equals(targetDate)) {
                actualDateShow.add(existingShow);
            }
        }

        LocalDateTime newShowStartTime = showUpdateResquestDTO.getShowTiming();
        LocalDateTime newShowEndTime = newShowStartTime.plusMinutes(movieService.duration(movie.getId()));

        for (Show existingShow : actualDateShow) {
            if (!existingShow.getId().equals(showId)) {
                LocalDateTime showStartTime = existingShow.getShowTimings();
                int existingShowDuration = movieService.duration(existingShow.getMovieId());
                LocalDateTime showEndTime = showStartTime.plusMinutes(existingShowDuration);

                if (newShowStartTime.isBefore(showEndTime) && newShowEndTime.isAfter(showStartTime)) {
                    throw new ShowTimingConflictException("New show timing conflicts with existing show in the same auditorium.");
                }
            }
        }

        show.setTheaterId(theatre.getId());
        show.setAudiId(auditorium.getId());
        show.setMovieId(movie.getId());
        show.setShowTimings(newShowStartTime);

        showRepo.save(show);

        auditorium.setShows(List.of(show));
        auditoriumService.saveAudi(auditorium);

        List<ShowSeat> showSeats = new ArrayList<>();
        for(Seat seat : auditorium.getSeatList()) {
            showSeats.add(showSeatService.generateShowSeat(show, seat));
        }
        show.setShowSeats(showSeats);

        return EntityToDTOMapper.convertShowEntitytoDTO(show);
    }

    @Override
    public Boolean deleteShow(UUID showId) {
        Show show = showRepo.findById(showId).orElseThrow(
                () -> new ShowNotFoundException("Show not found")
        );
        showRepo.delete(show);
        return true;
    }


}
