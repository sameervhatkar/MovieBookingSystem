package dev.sameer.bookmyshowreviseedition.Controller;

import dev.sameer.bookmyshowreviseedition.DTOs.TheatreRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.TheatreResponseDTO;
import dev.sameer.bookmyshowreviseedition.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @PostMapping("/createTheatre")
    public ResponseEntity<TheatreResponseDTO> createTheatre(@RequestBody TheatreRequestDTO theatreRequestDTO) {
        return ResponseEntity.ok(theatreService.createTheatre(theatreRequestDTO));
    }

    @GetMapping("/getTheatre/{theatreId}")
    public ResponseEntity<TheatreResponseDTO> getTheatreById(@PathVariable UUID theatreId) {
        return ResponseEntity.ok(theatreService.getTheatre(theatreId));
    }

    @GetMapping("/getTheatres")
    public ResponseEntity<List<TheatreResponseDTO>> getAllTheatres() {
        return ResponseEntity.ok(theatreService.getAllTheatres());
    }




}
