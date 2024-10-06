package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.DTOs.ShowRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.ShowResponseDTO;
import dev.sameer.bookmyshowreviseedition.Entity.*;
import dev.sameer.bookmyshowreviseedition.Exceptions.AuditoriumNotFoundException;
import dev.sameer.bookmyshowreviseedition.Exceptions.ShowTimingConflictException;
import dev.sameer.bookmyshowreviseedition.Mapper.EntityToDTOMapper;
import dev.sameer.bookmyshowreviseedition.Repo.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

        show.setTheaterId(theatre.getId());
        show.setAudiId(actualAudi.getId());
        show.setMovieId(movie.getId());
        show.setShowTimings(newShowStartTime);
        showRepo.save(show);

        actualAudi.setShows(List.of(show));

        List<ShowSeat> showSeats = new ArrayList<>();
        for(Seat seat : actualAudi.getSeatList()) {
            showSeats.add(showSeatService.generateShowSeat(show, seat));
        }
        show.setShowSeats(showSeats);
        return EntityToDTOMapper.convertShowEntitytoDTO(show, theatre.getTheatreName(), actualAudi.getAudiName(), movie.getMovieName());
    }


}
