package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.DTOs.UserLoginRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.UserResponseDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.UserSignInRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.UserUpdateRequestDTO;
import dev.sameer.bookmyshowreviseedition.Entity.Role;
import dev.sameer.bookmyshowreviseedition.Entity.User;
import dev.sameer.bookmyshowreviseedition.Exceptions.EmailAlreadyExistException;
import dev.sameer.bookmyshowreviseedition.Exceptions.UnauthorizeUserException;
import dev.sameer.bookmyshowreviseedition.Mapper.EntityToDTOMapper;
import dev.sameer.bookmyshowreviseedition.Repo.UserRepo;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public UserResponseDTO createUser(UserSignInRequestDTO signInRequestDTO) {
        if(userRepo.existsByEmail(signInRequestDTO.getEmail())) {
            throw new EmailAlreadyExistException("This email is already exist in our database, either enter new email-Id or forgot password");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUserName(signInRequestDTO.getUserName());
        user.setEmail(signInRequestDTO.getEmail());
        user.setFirstName(signInRequestDTO.getFirstName());
        user.setLastName(signInRequestDTO.getLastName());
        user.setPassword(encoder.encode(signInRequestDTO.getPassword()));
        user.setRole(Role.REGISTERED_USER);
        userRepo.save(user);
        return EntityToDTOMapper.convertUserEntitytoDTO(user);
    }

    @Override
    public UserResponseDTO logIn(UserLoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUserName(), loginRequestDTO.getPassword())
        );
        if(authentication.isAuthenticated()) {
            User user = userRepo.findUserByUserName(loginRequestDTO.getUserName());
            user.setToken(jwtService.generateToken(loginRequestDTO.getUserName()));
            userRepo.save(user);
            return EntityToDTOMapper.convertUserEntitytoDTO(user);
        }

        else
            throw new UnauthorizeUserException("UserName or Password is invalid");
    }

    @Override
    public UserResponseDTO updateUser(UserUpdateRequestDTO userUpdateRequestDTO) {
        //Here we can just update, firstName, lastName, and Email. (not userName and Password)
        String authHeader = request.getHeader("Authorization");
        String token;
        String username = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        User user = userRepo.findUserByUserName(username);
        user.setFirstName(userUpdateRequestDTO.getFirstName());
        user.setLastName(userUpdateRequestDTO.getLastName());
        user.setEmail(userUpdateRequestDTO.getEmail());
        userRepo.save(user);
        return EntityToDTOMapper.convertUserEntitytoDTO(user);
    }

    @Override
    public boolean deleteUser() {
        return true;
    }
}
