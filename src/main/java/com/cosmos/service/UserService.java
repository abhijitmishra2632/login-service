package com.cosmos.service;

import com.cosmos.model.User;
import com.cosmos.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String saveUserRegistration(User user) {
        String response = "";
        log.info("User to be added"+user.getUsername());
        boolean present = checkIfUserAlreadyRegistered(user);
        User encryptedUser = encryptUser(user);
        User savedUser = new User();
        try{
            if(!present){
                encryptedUser.setActive(true);
                savedUser = userRepository.save(encryptedUser);
                response="User saved successfully"+savedUser.getUsername();
            }
            else
                response ="User already registered Please check the Login ";
        }catch (Exception ex){
            log.error("Some Error while Storing User to DB"+ex+" "+user.getUsername());
        }
        log.info(response);
        return response;
    }

    private boolean checkIfUserAlreadyRegistered(User user) {
        boolean available = false;
        Optional<User> optionalUser = userRepository.findById(user.getUsername());
        log.info("User available: "+optionalUser);
        if(optionalUser.isPresent()){
            available=true;
        }
        return available;
    }

    private User encryptUser(User user) {
        User encryptedUser = new User();
        encryptedUser.setUsername(user.getUsername());
        encryptedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return encryptedUser;
    }

    public void validateLogin(User user) {
        log.info("Validating for user :"+user.toString());

    }
}
