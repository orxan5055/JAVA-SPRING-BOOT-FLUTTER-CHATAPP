package com.chatapp.controllers;

import com.chatapp.model.User;
import com.chatapp.repos.UserRepo;
import com.chatapp.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/home")
    public String home() {
        return "hello there";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        if (userService.authenticate(email, password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User newUser) {
        Optional<User> user = userRepo.findByEmail(newUser.getEmail());
        if (user.isPresent()) {
            return ResponseEntity.ok("Email has been taken");
        } else {
            userService.saveUser(newUser);
            if (!newUser.getEmail().isEmpty() && !newUser.getPassword().isEmpty())
                return ResponseEntity.ok("User added successfully");
            else
                return ResponseEntity.ok("User add has been failed");
        }
    }
}