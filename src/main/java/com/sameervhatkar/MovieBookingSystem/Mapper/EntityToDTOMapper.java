package com.sameervhatkar.MovieBookingSystem.Mapper;

import com.sameervhatkar.MovieBookingSystem.dto.*;
import com.sameervhatkar.MovieBookingSystem.entity.*;

public class EntityToDTOMapper {

    public static GetMovieResponseDTOForClient convertMovieEntityToDTOForClient(Movie movie) {
        GetMovieResponseDTOForClient response = new GetMovieResponseDTOForClient();

        response.setTitle(movie.getTitle());
        response.setDurationInMinutes(movie.getDurationInMinutes());
        response.setGenre(movie.getGenre());
        response.setOriginalLanguage(movie.getOriginalLanguage());
        response.setReleaseDate(movie.getReleaseDate());

        return response;
    }

    public static GetMovieResponseDTOForUser convertMovieEntityToDTOForUser(Movie movie) {
        GetMovieResponseDTOForUser response = new GetMovieResponseDTOForUser();

        response.setTitle(movie.getTitle());
        response.setDurationInMinutes(movie.getDurationInMinutes());
        response.setGenre(movie.getGenre());
        response.setOriginalLanguage(movie.getOriginalLanguage());
        response.setReleaseDate(movie.getReleaseDate());

        return response;
    }

    public static CreateMovieResponseDTO convertCreateMovieEntityToDTOForClient(Movie movie) {
        CreateMovieResponseDTO response = new CreateMovieResponseDTO();

        response.setId(movie.getId());
        response.setTitle(movie.getTitle());
        response.setDurationInMinutes(movie.getDurationInMinutes());
        response.setGenre(movie.getGenre());
        response.setOriginalLanguage(movie.getOriginalLanguage());
        response.setReleaseDate(movie.getReleaseDate());
        response.setStatus(movie.getStatus());
        response.setCreatedAt(movie.getCreatedAt());
        response.setUpdatedAt(movie.getUpdatedAt());
        return response;
    }

    public static TheatreResponseDTO convertTheatreEntityToDTO(Theatre theatre) {
        TheatreResponseDTO responseDTO = new TheatreResponseDTO();

        responseDTO.setId(theatre.getId());
        responseDTO.setName(theatre.getName());
        responseDTO.setAddress(theatre.getAddress());
        responseDTO.setCity(theatre.getCity());
        responseDTO.setState(theatre.getState());
        responseDTO.setPincode(theatre.getPincode());
        responseDTO.setCreatedAt(theatre.getCreatedAt());
        responseDTO.setUpdatedAt(theatre.getUpdatedAt());

        return responseDTO;
    }

    public static AudiResponseDTO convertAudiEntityToDTO(Auditorium auditorium) {
        AudiResponseDTO responseDTO = new AudiResponseDTO();

        responseDTO.setId(auditorium.getId());
        responseDTO.setName(auditorium.getName());
        responseDTO.setTheatreId(auditorium.getTheatre().getId());
        responseDTO.setScreenType(auditorium.getScreenType());
        responseDTO.setCreatedAt(auditorium.getCreatedAt());
        responseDTO.setUpdatedAt(auditorium.getUpdatedAt());

        return responseDTO;
    }

    public static SeatResponseDTO convertSeatEntityToDTO(Seat seat) {
        SeatResponseDTO responseDTO = new SeatResponseDTO();

        responseDTO.setId(seat.getId());
        responseDTO.setAuditoriumId(seat.getAuditorium().getId());
        responseDTO.setSeat_number(seat.getSeat_number());
        responseDTO.setRow_label(seat.getRow_label());
        responseDTO.setSeatType(seat.getSeatType());
        responseDTO.setCreatedAt(seat.getCreatedAt());
        responseDTO.setUpdatedAt(seat.getUpdatedAt());

        return responseDTO;
    }

    public static ShowResponseDTO convertShowEntityToDTO(Show show) {
        ShowResponseDTO responseDTO = new ShowResponseDTO();

        responseDTO.setId(show.getId());
        responseDTO.setMovieId(show.getMovie().getId());
        responseDTO.setAuditoriumId(show.getAuditorium().getId());
        responseDTO.setStartTime(show.getStartTime());
        responseDTO.setEndTime(show.getEndTime());
        responseDTO.setBasePrice(show.getBasePrice());
        responseDTO.setCreatedAt(show.getCreatedAt());
        responseDTO.setUpdatedAt(show.getUpdatedAt());

        return responseDTO;
    }

    public static ShowSeatResponseDTO convertShowSeatEntityToDTO(ShowSeat showSeat) {

        ShowSeatResponseDTO responseDTO = new ShowSeatResponseDTO();

        responseDTO.setId(showSeat.getId());
        responseDTO.setShowId(showSeat.getShow().getId());
        responseDTO.setSeatId(showSeat.getSeat().getId());
        responseDTO.setStatus(showSeat.getStatus());

        if (showSeat.getLockedBy() != null) {
            responseDTO.setLockedByUserId(showSeat.getLockedBy().getId());
        }

        responseDTO.setLockExpiryTime(showSeat.getLockExpiryTime());
        responseDTO.setCreatedAt(showSeat.getCreatedAt());
        responseDTO.setUpdatedAt(showSeat.getUpdatedAt());

        return responseDTO;
    }

    public static UserResponseDTO convertUserEntityToDTO(User user) {

        UserResponseDTO responseDTO = new UserResponseDTO();

        responseDTO.setId(user.getId());
        responseDTO.setName(user.getName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setPhoneNumber(user.getPhoneNumber());
        responseDTO.setCreatedAt(user.getCreatedAt());
        responseDTO.setUpdatedAt(user.getUpdatedAt());

        return responseDTO;
    }

    public static BookingResponseDTO convertBookingEntityToDTO(Booking booking) {
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();

        bookingResponseDTO.setId(booking.getId());
        bookingResponseDTO.setUserId(booking.getUser().getId());
        bookingResponseDTO.setShowId(booking.getShow().getId());
        bookingResponseDTO.setStatus(booking.getStatus());
        bookingResponseDTO.setTotalAmount(booking.getTotalAmount());
        bookingResponseDTO.setCreatedAt(booking.getCreatedAt());
        bookingResponseDTO.setUpdatedAt(booking.getUpdatedAt());

        return bookingResponseDTO;
    }

    public static PaymentResponseDTO convertPaymentEntityToDTO(Payment payment) {
        PaymentResponseDTO responseDTO = new PaymentResponseDTO();

        responseDTO.setId(payment.getId());
        responseDTO.setBookingId(payment.getBooking().getId());
        responseDTO.setAmount(payment.getAmount());
        responseDTO.setStatus(payment.getStatus());
        responseDTO.setPaymentGatewayReferenceId(payment.getPaymentGatewayReferenceId());
        responseDTO.setCreatedAt(payment.getCreatedAt());
        responseDTO.setUpdatedAt(payment.getUpdatedAt());

        return responseDTO;
    }
}

