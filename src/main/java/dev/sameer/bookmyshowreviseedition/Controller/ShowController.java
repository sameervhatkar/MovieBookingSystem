package dev.sameer.bookmyshowreviseedition.Controller;

import dev.sameer.bookmyshowreviseedition.DTOs.ShowRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.ShowResponseDTO;
import dev.sameer.bookmyshowreviseedition.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/createShow")
    public ResponseEntity<ShowResponseDTO> createShow(@RequestBody ShowRequestDTO showRequestDTO) {
        return ResponseEntity.ok(showService.createShow(showRequestDTO));
    }
}
