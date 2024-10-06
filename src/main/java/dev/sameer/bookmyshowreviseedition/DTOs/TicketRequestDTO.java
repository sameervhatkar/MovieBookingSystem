package dev.sameer.bookmyshowreviseedition.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TicketRequestDTO {
    private List<UUID> showSeatIds;
}
