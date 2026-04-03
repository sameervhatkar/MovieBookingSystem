package com.sameervhatkar.MovieBookingSystem.service;

import com.sameervhatkar.MovieBookingSystem.entity.MyUserDetails;
import com.sameervhatkar.MovieBookingSystem.entity.User;
import com.sameervhatkar.MovieBookingSystem.exceptions.UserNotFoundException;
import com.sameervhatkar.MovieBookingSystem.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(username);
        if(user == null)
            throw new UserNotFoundException("User not found.");
        return new MyUserDetails(user);
    }
}
