package com.cosmos.controller;

import com.cosmos.model.User;
import com.cosmos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String getRegisterPage(){
        return "Can access register Page";
    }
    @PostMapping
    public String saveUserRegistration(@RequestBody User user){
        return userService.saveUserRegistration(user);
    }
    @PutMapping("/{username}")
    public void forgotPassword(@PathVariable Long username,@RequestBody User user){
        userService.forgotPassword(username,user);
    }

}
