package com.cosmos.controller;

import com.cosmos.model.JWTRequest;
import com.cosmos.model.JWTResponse;
import com.cosmos.service.UserService;
import com.cosmos.utility.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtility;
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping
    public String checkLogin(){
        log.info("Login method called with get request...");
        return "Success";
    }
    @GetMapping("/admin")
    public String checkAdminLogin(){
        log.info("Login method called with get request for admin...");
        return "Success page for admin";
    }
    @PostMapping
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest)throws Exception{
        log.info("Post method called...");
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtility.generateToken(userDetails);

        return new JWTResponse(token);
    }

}
