package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.DTOs.TicketResponseDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.TicketRequestDTO;

public interface TicketService {
    TicketResponseDTO bookTicket(TicketRequestDTO ticketRequestDTO);
}
