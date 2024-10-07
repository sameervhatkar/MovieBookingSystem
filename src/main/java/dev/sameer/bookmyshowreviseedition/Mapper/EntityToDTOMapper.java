package dev.sameer.bookmyshowreviseedition.Mapper;

import dev.sameer.bookmyshowreviseedition.DTOs.*;
import dev.sameer.bookmyshowreviseedition.Entity.*;
import dev.sameer.bookmyshowreviseedition.Service.MovieService;

import java.util.ArrayList;
import java.util.List;

public class EntityToDTOMapper {


    public static CityResponseDTO convertCityEntitytoDTO(City city) {
        CityResponseDTO cityResponseDTO = new CityResponseDTO();
        cityResponseDTO.setCityName(city.getCityName());
        List<Theatre> theatres = city.getTheatreList();
        List<String> theatreList = new ArrayList<>();
        if(!theatres.isEmpty()) {
            for (Theatre theatre : theatres)
                theatreList.add(theatre.getTheatreName());
        }
        cityResponseDTO.setTheatreName(theatreList);
        return cityResponseDTO;
    }

    public static UserResponseDTO convertUserEntitytoDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setFullName(user.getFirstName() + " " + user.getLastName());
        userResponseDTO.setUserName(user.getUserName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRole(user.getRole().toString());
        userResponseDTO.setTickets(user.getTickets());
        userResponseDTO.setToken(user.getToken());
        return userResponseDTO;
    }

    public static TheatreResponseDTO convertTheatreEntitytoDTO(Theatre theatre) {
        TheatreResponseDTO theatreResponseDTO = new TheatreResponseDTO();
        theatreResponseDTO.setTheatreName(theatre.getTheatreName());
        theatreResponseDTO.setTheatreAddress(theatre.getTheatreAddress());

        List<String> moviesName = new ArrayList<>();
        List<Movie> movieList = theatre.getMovieList();

        // Check if movieList is null before proceeding
        if (movieList != null && !movieList.isEmpty()) {
            for (Movie movie : movieList) {
                moviesName.add(movie.getMovieName());
            }
        }

        theatreResponseDTO.setMoviesName(moviesName);
        return theatreResponseDTO;
    }

    public static AuditoriumResponseDTO convertAuditoriumEntitytoDTO(Auditorium auditorium) {
        AuditoriumResponseDTO auditoriumResponseDTO = new AuditoriumResponseDTO();
        auditoriumResponseDTO.setTheatreName(auditorium.getTheatreName());
        auditoriumResponseDTO.setAudiName(auditorium.getAudiName());
        auditoriumResponseDTO.setCapacity(auditorium.getCapacity());
        return auditoriumResponseDTO;
    }

    public static MovieResponseDTO convertMovieEntitytoDTO(Movie movie) {
        MovieResponseDTO movieResponseDTO = new MovieResponseDTO();
        movieResponseDTO.setMovieName(movie.getMovieName());
        movieResponseDTO.setReleaseDate(movie.getReleaseDate());
        movieResponseDTO.setActorsName(movie.getActorsName());
        movieResponseDTO.setDirectorName(movie.getDirectorName());
        movieResponseDTO.setMovieDuration(movie.getMovieDuration());
        return movieResponseDTO;
    }

    public static ShowResponseDTO convertShowEntitytoDTO(Show show, String theatreName, String audiName, String movieName) {
        ShowResponseDTO showResponseDTO = new ShowResponseDTO();
        showResponseDTO.setTheatreName(theatreName);
        showResponseDTO.setAudiName(audiName);
        showResponseDTO.setMovieName(movieName);
        showResponseDTO.setShowTime(show.getShowTimings());
        return showResponseDTO;
    }

    public static TicketResponseDTO convertTicketEntitytoDTO(Ticket ticket, String movieName) {

        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
        ticketResponseDTO.setTicketPrice(ticket.getTicketPrice());
        ticketResponseDTO.setMovieName(movieName);
        ticketResponseDTO.setBookingTime(ticket.getBookingTime());
        ticketResponseDTO.setShowTiming(ticket.getShow().getShowTimings());
        ticketResponseDTO.setStatus(ticket.getTicketStatus());
        List<Seat> seatList = new ArrayList<>();
        for(ShowSeat showSeat : ticket.getShowSeats()) {
            seatList.add(showSeat.getSeat());
        }
        ticketResponseDTO.setBookedSeats(seatList);
        return ticketResponseDTO;

    }
}
