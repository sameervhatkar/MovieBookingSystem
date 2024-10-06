package dev.sameer.bookmyshowreviseedition.DTOs;

import dev.sameer.bookmyshowreviseedition.Entity.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Timer;

@Getter
@Setter
public class UserResponseDTO {
    private String userName;
    private String fullName;
    private String email;
    private String role;
    private List<Ticket> tickets;
    private String token;
}
