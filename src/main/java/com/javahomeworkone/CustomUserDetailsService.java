package com.javahomeworkone;

import com.javahomeworkone.user.User;
import com.javahomeworkone.user.UserNotFoundException;
import com.javahomeworkone.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = repo.findByEmail(email);
        if (user == null){
            try {
                throw new UserNotFoundException("User not found");
            } catch (UserNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return new CustomUserDetails(user);
    }
}
