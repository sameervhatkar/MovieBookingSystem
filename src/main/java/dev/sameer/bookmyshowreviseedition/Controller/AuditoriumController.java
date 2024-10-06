package dev.sameer.bookmyshowreviseedition.Controller;

import dev.sameer.bookmyshowreviseedition.DTOs.AuditoriumRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.AuditoriumResponseDTO;
import dev.sameer.bookmyshowreviseedition.Service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuditoriumController {

    @Autowired
    private AuditoriumService auditoriumService;

    @PostMapping("/createAudi")
    public ResponseEntity<AuditoriumResponseDTO> createAudi(@RequestBody AuditoriumRequestDTO auditoriumRequestDTO) {
        return ResponseEntity.ok(auditoriumService.createAuditorium(auditoriumRequestDTO));
    }


}
