package dev.sameer.bookmyshowreviseedition.Service;


import dev.sameer.bookmyshowreviseedition.DTOs.UserLoginRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.UserResponseDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.UserSignInRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.UserUpdateRequestDTO;

public interface UserService {
    UserResponseDTO createUser(UserSignInRequestDTO signInRequestDTO);
    UserResponseDTO logIn(UserLoginRequestDTO loginRequestDTO);
    UserResponseDTO updateUser(UserUpdateRequestDTO userUpdateRequestDTO);
    boolean deleteUser();
}
