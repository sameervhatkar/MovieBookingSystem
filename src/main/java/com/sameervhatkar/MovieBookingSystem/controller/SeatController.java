package com.sameervhatkar.MovieBookingSystem.controller;

import com.sameervhatkar.MovieBookingSystem.dto.CreateSeatRequestDTO;
import com.sameervhatkar.MovieBookingSystem.dto.GenerateSeatsRequestDTO;
import com.sameervhatkar.MovieBookingSystem.dto.SeatResponseDTO;
import com.sameervhatkar.MovieBookingSystem.dto.UpdateSeatRequestDTO;
import com.sameervhatkar.MovieBookingSystem.service.SeatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SeatController {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    //CRUD

    @PostMapping("/seats/generate")
    public ResponseEntity<String> generateSeats(@RequestBody GenerateSeatsRequestDTO requestDTO) {

        seatService.generateSeats(requestDTO);

        return ResponseEntity.ok("Seats generated successfully");
    }

    @PostMapping("/admin/seats")
    public ResponseEntity<SeatResponseDTO> createSeat(@Valid @RequestBody CreateSeatRequestDTO requestDTO) {
        SeatResponseDTO responseDTO = seatService.createSeat(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/admin/seats/{id}")
    public ResponseEntity<SeatResponseDTO> getSeatById(@PathVariable Long id) {
        SeatResponseDTO responseDTO = seatService.getSeatById(id);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/admin/seats")
    public ResponseEntity<SeatResponseDTO> updateSeat(@Valid @RequestBody UpdateSeatRequestDTO requestDTO) {
        SeatResponseDTO responseDTO = seatService.updateSeat(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/admin/seats/{id}")
    public ResponseEntity<Void> deleteSeatById(@PathVariable Long id) {
        seatService.deleteSeat(id);

        return ResponseEntity.noContent().build();
    }
}
