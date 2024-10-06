package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.Entity.MyUserDetails;
import dev.sameer.bookmyshowreviseedition.Entity.User;
import dev.sameer.bookmyshowreviseedition.Repo.UserRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUserName(username);
        if(user == null)
            throw new UsernameNotFoundException("User with username doesn't exist in our database");
        else
            return new MyUserDetails(user);
    }
}
