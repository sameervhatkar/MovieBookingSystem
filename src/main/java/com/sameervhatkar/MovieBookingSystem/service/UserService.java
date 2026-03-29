package com.sameervhatkar.MovieBookingSystem.service;

import com.sameervhatkar.MovieBookingSystem.Mapper.EntityToDTOMapper;
import com.sameervhatkar.MovieBookingSystem.dto.CreateUserRequestDTO;
import com.sameervhatkar.MovieBookingSystem.dto.UpdateUserRequestDTO;
import com.sameervhatkar.MovieBookingSystem.dto.UserResponseDTO;
import com.sameervhatkar.MovieBookingSystem.entity.User;
import com.sameervhatkar.MovieBookingSystem.exceptions.UserNotFoundException;
import com.sameervhatkar.MovieBookingSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(CreateUserRequestDTO requestDTO){
        User user = new User();
        user.setName(requestDTO.getName());
        user.setEmail(requestDTO.getEmail());
        user.setPhoneNumber(requestDTO.getPhoneNumber());
        User savedUser = userRepository.save(user);
        return EntityToDTOMapper.convertUserEntityToDTO(savedUser);
    }

    public UserResponseDTO getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return EntityToDTOMapper.convertUserEntityToDTO(user);
    }

    public UserResponseDTO updateUser(UpdateUserRequestDTO requestDTO){
        User user = userRepository.findById(requestDTO.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if(requestDTO.getName() != null)
            user.setName(requestDTO.getName());
        if(requestDTO.getEmail() != null)
            user.setEmail(requestDTO.getEmail());
        if(requestDTO.getPhoneNumber() != null)
            user.setPhoneNumber(requestDTO.getPhoneNumber());
        User savedUser = userRepository.save(user);
        return EntityToDTOMapper.convertUserEntityToDTO(savedUser);
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }
}