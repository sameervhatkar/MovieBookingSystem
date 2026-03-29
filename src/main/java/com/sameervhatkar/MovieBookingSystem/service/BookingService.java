package com.sameervhatkar.MovieBookingSystem.service;

import com.sameervhatkar.MovieBookingSystem.Mapper.EntityToDTOMapper;
import com.sameervhatkar.MovieBookingSystem.dto.BookingResponseDTO;
import com.sameervhatkar.MovieBookingSystem.dto.CreateBookingRequestDTO;
import com.sameervhatkar.MovieBookingSystem.entity.*;
import com.sameervhatkar.MovieBookingSystem.enums.BookingStatus;
import com.sameervhatkar.MovieBookingSystem.enums.ShowSeatStatus;
import com.sameervhatkar.MovieBookingSystem.exceptions.BookingNotFoundException;
import com.sameervhatkar.MovieBookingSystem.exceptions.ShowNotFoundException;
import com.sameervhatkar.MovieBookingSystem.exceptions.UserNotFoundException;
import com.sameervhatkar.MovieBookingSystem.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final BookingRepository bookingRepository;
    private final BookingSeatService bookingSeatService;
    private final BookingSeatRepository bookingSeatRepository;

    public BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, BookingRepository bookingRepository, BookingSeatService bookingSeatService, BookingSeatRepository bookingSeatRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.bookingSeatService = bookingSeatService;
        this.bookingSeatRepository = bookingSeatRepository;
    }

    @Transactional
    public BookingResponseDTO createBooking(CreateBookingRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.getUserId()).orElseThrow(
                () -> new UserNotFoundException("User not found.")
        );

        Show show = showRepository.findById(requestDTO.getShowId()).orElseThrow(
                () -> new ShowNotFoundException("Show not found.")
        );

        List<ShowSeat> showSeatList = showSeatRepository.findAllById(requestDTO.getShowSeatIds());

        for(ShowSeat showSeat : showSeatList) {
            if(!showSeat.getShow().getId().equals(requestDTO.getShowId()))
                throw new RuntimeException("Seat does not belong to this show.");
            if(showSeat.getStatus() != ShowSeatStatus.AVAILABLE)
                throw new RuntimeException("Seat not available");

            showSeat.setStatus(ShowSeatStatus.LOCKED);
            showSeat.setLockedBy(user);
            showSeat.setLockExpiryTime(LocalDateTime.now().plusMinutes(5));
            showSeatRepository.save(showSeat);
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setStatus(BookingStatus.PENDING);

        Booking savedBooking = bookingRepository.save(booking);
        double totalAmount = 0.0;
        for(ShowSeat showSeat : showSeatList) {
            double price = showSeat.getShow().getBasePrice();
            bookingSeatService.createBookingSeat(savedBooking, showSeat, price);
            totalAmount += price;
        }
        savedBooking.setTotalAmount(totalAmount);
        bookingRepository.save(savedBooking);

        return EntityToDTOMapper.convertBookingEntityToDTO(savedBooking);
    }

    public BookingResponseDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(
                () -> new BookingNotFoundException("Booking with this id is not found.")
        );

        return EntityToDTOMapper.convertBookingEntityToDTO(booking);
    }

    public BookingResponseDTO cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new RuntimeException("Booking already cancelled");
        }

        List<BookingSeat> bookingSeats = bookingSeatRepository.findByBookingId(bookingId);

        for (BookingSeat bookingSeat : bookingSeats) {
            ShowSeat showSeat = bookingSeat.getShowSeat();
            showSeat.setStatus(ShowSeatStatus.AVAILABLE);
            showSeat.setLockedBy(null);
            showSeat.setLockExpiryTime(null);
            showSeatRepository.save(showSeat);
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        return EntityToDTOMapper.convertBookingEntityToDTO(booking);
    }


}
