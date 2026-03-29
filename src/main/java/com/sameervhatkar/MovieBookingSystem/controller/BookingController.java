package com.sameervhatkar.MovieBookingSystem.controller;

import com.sameervhatkar.MovieBookingSystem.dto.BookingResponseDTO;
import com.sameervhatkar.MovieBookingSystem.dto.CreateBookingRequestDTO;
import com.sameervhatkar.MovieBookingSystem.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/bookings")
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody CreateBookingRequestDTO createBookingRequestDTO) {
        BookingResponseDTO bookingResponseDTO = bookingService.createBooking(createBookingRequestDTO);
        return ResponseEntity.ok(bookingResponseDTO);
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long id) {
        BookingResponseDTO responseDTO = bookingService.getBookingById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<BookingResponseDTO> cancelBooking(@PathVariable Long id) {
        BookingResponseDTO responseDTO = bookingService.cancelBooking(id);
        return ResponseEntity.ok(responseDTO);
    }
}
