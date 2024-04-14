package com.madbeats.controller;
/**
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.madbeats.entity.User;
import com.madbeats.repository.UserRepository;


@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private  PasswordEncoder passwordEncoder;
	
	@PostMapping ("api/register")
	public ResponseEntity registerUser(@RequestBody User user) {
	    try {
	        // Log para verificar si se recibe correctamente la solicitud de registro
	        System.out.println("Received registration request for user: " + user.getEmail());
	        
	        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
	            System.out.println("Email already registered: " + user.getEmail());
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
	        }
	        
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        User save = userRepository.save(user);
	        // Log para verificar si el usuario se guarda correctamente en la base de datos
	        System.out.println("User registered successfully: " + save.getEmail());
	        
	        return ResponseEntity.ok(HttpStatus.CREATED);
	    } catch (Exception e) {
	        // Log para cualquier excepción durante el registro
	        System.err.println("Error registering user: " + e.getMessage());
	        return ResponseEntity.internalServerError().body(e.getMessage());
	    }
	}

}
**/