package com.sameervhatkar.MovieBookingSystem.controller;

import com.sameervhatkar.MovieBookingSystem.dto.CreateTheatreRequestDTO;
import com.sameervhatkar.MovieBookingSystem.dto.TheatreResponseDTO;
import com.sameervhatkar.MovieBookingSystem.dto.UpdateTheatreRequestDTO;
import com.sameervhatkar.MovieBookingSystem.service.TheatreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TheatreController {

    private TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    //Crud

    @PostMapping("/admin/theatres")
    public ResponseEntity<TheatreResponseDTO> createTheatre(@RequestBody CreateTheatreRequestDTO requestDTO) {
        TheatreResponseDTO responseDTO = theatreService.createTheatre(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/admin/theatres/{id}")
    public ResponseEntity<TheatreResponseDTO> getTheatreById(@PathVariable Long id) {
        TheatreResponseDTO responseDTO = theatreService.getTheatreById(id);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/admin/theatres/")
    public ResponseEntity<TheatreResponseDTO> updateTheatre(@RequestBody UpdateTheatreRequestDTO requestDTO) {
        TheatreResponseDTO responseDTO = theatreService.updateTheatre(requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/admin/theatres/{id}")
    public ResponseEntity<Void> deleteTheatreById(@PathVariable Long id) {
        theatreService.deleteTheatre(id);

        return ResponseEntity.noContent().build();
    }
}

