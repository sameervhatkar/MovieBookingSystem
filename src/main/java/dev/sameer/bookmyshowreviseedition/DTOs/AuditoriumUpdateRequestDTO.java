package dev.sameer.bookmyshowreviseedition.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AuditoriumUpdateRequestDTO {
    private UUID audiId;
    private String audiName;
    private int capacity;
    private List<UUID> seatIds;
}
