package dev.sameer.bookmyshowreviseedition.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}
