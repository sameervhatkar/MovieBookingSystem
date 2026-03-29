package com.sameervhatkar.MovieBookingSystem.controller;

import com.sameervhatkar.MovieBookingSystem.dto.AudiResponseDTO;
import com.sameervhatkar.MovieBookingSystem.dto.CreateAudiRequestDTO;
import com.sameervhatkar.MovieBookingSystem.dto.UpdateAuditoriumRequestDTO;
import com.sameervhatkar.MovieBookingSystem.service.AuditoriumService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuditoriumController {
    private AuditoriumService auditoriumService;

    public AuditoriumController(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    //CRUD
    @PostMapping("/admin/audi")
    public ResponseEntity<AudiResponseDTO> createAuditorium(@Valid @RequestBody CreateAudiRequestDTO requestDTO) {
        AudiResponseDTO responseDTO = auditoriumService.createAuditorium(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/admin/audi/{id}")
    public ResponseEntity<AudiResponseDTO> getAuditoriumById(@PathVariable Long id) {
        AudiResponseDTO responseDTO = auditoriumService.getAuditoriumById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/admin/audi")
    public ResponseEntity<AudiResponseDTO> updateAuditorium(@RequestBody UpdateAuditoriumRequestDTO requestDTO) {
        AudiResponseDTO responseDTO = auditoriumService.updateAuditorium(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/admin/audi/{id}")
    public ResponseEntity<Void> deleteAuditorium(@PathVariable Long id) {
        auditoriumService.deleteAuditorium(id);
        return ResponseEntity.noContent().build();
    }

}
