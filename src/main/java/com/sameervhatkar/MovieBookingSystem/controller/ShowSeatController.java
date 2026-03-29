package com.sameervhatkar.MovieBookingSystem.controller;

import com.sameervhatkar.MovieBookingSystem.dto.SeatResponseDTO;
import com.sameervhatkar.MovieBookingSystem.dto.ShowSeatResponseDTO;
import com.sameervhatkar.MovieBookingSystem.service.ShowSeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowSeatController {
    private final ShowSeatService showSeatService;

    public ShowSeatController(ShowSeatService showSeatService) {
        this.showSeatService = showSeatService;
    }

    @GetMapping("/shows/{showId}/seats")
    public ResponseEntity<List<SeatResponseDTO>> getSeatByShowId(@PathVariable Long showId) {
        List<SeatResponseDTO> seatResponseDTOS = showSeatService.getSeatByShow(showId);

        return ResponseEntity.ok(seatResponseDTOS);
    }

    @GetMapping("/show-seats/{showId}")
    public ResponseEntity<ShowSeatResponseDTO> getShowSeatById(@PathVariable Long showId) {
        ShowSeatResponseDTO showSeatResponseDTO = showSeatService.getShowSeatById(showId);

        return ResponseEntity.ok(showSeatResponseDTO);
    }
}
