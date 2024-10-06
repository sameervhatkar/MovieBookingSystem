package dev.sameer.bookmyshowreviseedition.Controller;

import dev.sameer.bookmyshowreviseedition.DTOs.UserLoginRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.UserResponseDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.UserSignInRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.UserUpdateRequestDTO;
import dev.sameer.bookmyshowreviseedition.Entity.User;
import dev.sameer.bookmyshowreviseedition.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserSignInRequestDTO signInRequestDTO) {
        return ResponseEntity.ok(userService.createUser(signInRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserLoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(userService.logIn(loginRequestDTO));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        return ResponseEntity.ok(userService.updateUser(userUpdateRequestDTO));
    }
}
