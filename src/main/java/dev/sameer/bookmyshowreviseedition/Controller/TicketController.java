package dev.sameer.bookmyshowreviseedition.Controller;

import dev.sameer.bookmyshowreviseedition.DTOs.TicketRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.TicketResponseDTO;
import dev.sameer.bookmyshowreviseedition.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/ticket")
    public ResponseEntity<TicketResponseDTO> bookTicket(@RequestBody TicketRequestDTO ticketRequestDTO) {
        return ResponseEntity.ok(ticketService.bookTicket(ticketRequestDTO));

    }
}
