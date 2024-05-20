package com.madbeats.controller;

import com.madbeats.entity.DefaultUser;
import com.madbeats.repository.DefaultUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/default_user")
public class DefaultUserController {

    @Autowired
    private final DefaultUserRepository defaultUserRepository;

    @Autowired
    public DefaultUserController(DefaultUserRepository defaultUserRepository) {
        this.defaultUserRepository = defaultUserRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody DefaultUser user) {
        Optional<DefaultUser> existingUser = defaultUserRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            System.out.println("User already registered");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already registered");
        }
        defaultUserRepository.save(user);
        System.out.println("User successfully registered");
        return ResponseEntity.status(HttpStatus.CREATED).body("User successfully registered");
    }

    @PostMapping("/login")
    public ResponseEntity<DefaultUser> loginUser(@RequestBody DefaultUser user) {
        Optional<DefaultUser> existingUser = defaultUserRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            DefaultUser foundUser = existingUser.get();
            if (foundUser.getPassword().equals(user.getPassword())) {
                System.out.println("User logged in: " + foundUser.toString());
                return ResponseEntity.ok(foundUser);
            } else {
                System.out.println("Wrong password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            System.out.println("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
