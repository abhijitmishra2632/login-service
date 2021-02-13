package com.cosmos.controller;

import com.cosmos.model.JWTRequest;
import com.cosmos.model.JWTResponse;
import com.cosmos.model.User;
import com.cosmos.service.UserService;
import com.cosmos.utility.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtility;
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/login")
    public String checkLogin(){
        log.info("Login method called with get request...");
        return "Success";
    }
    @GetMapping("/login/admin")
    public String checkAdminLogin(){
        log.info("Login method called with get request for admin...");
        return "Success page for admin";
    }
    @PostMapping("/login")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest)throws Exception{
        log.info("Post method called...");
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtility.generateToken(userDetails);

        return new JWTResponse(token);
    }
    @GetMapping("/register")
    public String getRegisterPage(){
        log.info("/register called");
        return "Can access register Page";
    }
    @PostMapping("/register")
    public String saveUserRegistration(@RequestBody User user){
        return userService.saveUserRegistration(user);
    }
    @PutMapping("/register/{username}")
    public void forgotPassword(@PathVariable Long username,@RequestBody User user){
        userService.forgotPassword(username,user);
    }

}
