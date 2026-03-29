package com.sameervhatkar.MovieBookingSystem.controller;

import com.sameervhatkar.MovieBookingSystem.dto.CreateShowRequestDTO;
import com.sameervhatkar.MovieBookingSystem.dto.ShowResponseDTO;
import com.sameervhatkar.MovieBookingSystem.dto.UpdateShowRequestDTO;
import com.sameervhatkar.MovieBookingSystem.service.ShowService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    // CREATE
    @PostMapping("/admin/shows")
    public ResponseEntity<ShowResponseDTO> createShow(@Valid @RequestBody CreateShowRequestDTO requestDTO) {
        ShowResponseDTO responseDTO = showService.createShow(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // GET
    @GetMapping("/admin/shows/{id}")
    public ResponseEntity<ShowResponseDTO> getShowById(@PathVariable Long id) {
        ShowResponseDTO responseDTO = showService.getShowById(id);
        return ResponseEntity.ok(responseDTO);
    }

    // UPDATE
    @PutMapping("/admin/shows")
    public ResponseEntity<ShowResponseDTO> updateShow(@Valid @RequestBody UpdateShowRequestDTO requestDTO) {
        ShowResponseDTO responseDTO = showService.updateShow(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // DELETE
    @DeleteMapping("/admin/shows/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }
}