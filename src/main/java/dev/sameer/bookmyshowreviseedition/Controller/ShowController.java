package dev.sameer.bookmyshowreviseedition.Controller;

import dev.sameer.bookmyshowreviseedition.DTOs.ShowRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.ShowResponseDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.ShowUpdateResquestDTO;
import dev.sameer.bookmyshowreviseedition.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/createShow")
    public ResponseEntity<ShowResponseDTO> createShow(@RequestBody ShowRequestDTO showRequestDTO) {
        return ResponseEntity.ok(showService.createShow(showRequestDTO));
    }

    @GetMapping("/getShowById/{showId}")
    public ResponseEntity<ShowResponseDTO> getShowById(@PathVariable UUID showId) {
        return ResponseEntity.ok(showService.getShowById(showId));
    }

    @GetMapping("getShowByTheatre/{theatreId}")
    public ResponseEntity<List<ShowResponseDTO>> getShowByTheatre(@PathVariable UUID theatreId) {
        return ResponseEntity.ok(showService.getShowByTheatre(theatreId));
    }

    @GetMapping("getShowByAudi/{audiId}")
    public ResponseEntity<List<ShowResponseDTO>> getShowByAudi(@PathVariable UUID audiId) {
        return ResponseEntity.ok(showService.getShowByAudi(audiId));
    }

    @PutMapping("/updateShow/{showId}")
    public ResponseEntity<ShowResponseDTO> updateShow(@PathVariable UUID showId, @RequestBody ShowUpdateResquestDTO showUpdateResquestDTO) {
        return ResponseEntity.ok(showService.updateShow(showId, showUpdateResquestDTO));
    }

    @DeleteMapping("/deleteShow/{showId}")
    public ResponseEntity<Boolean> deleteShow(@PathVariable UUID showId) {
        return ResponseEntity.ok(showService.deleteShow(showId));
    }
}
