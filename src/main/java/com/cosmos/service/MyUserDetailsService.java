package com.cosmos.service;

import com.cosmos.model.User;
import com.cosmos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
@Configuration
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Long mobileNumber =Long.parseLong(username);
        Optional<User> optionalUser=userRepository.findById(mobileNumber);
        if(!optionalUser.isPresent())
            throw new UsernameNotFoundException("Create a Account First");
        return new MyUserDetails(optionalUser.get());
    }
}
