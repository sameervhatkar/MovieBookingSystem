package dev.sameer.bookmyshowreviseedition.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "bms_user")
public class User extends BaseModel {
    private String userName;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    @OneToMany
    private List<Ticket> tickets;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String token;
}
