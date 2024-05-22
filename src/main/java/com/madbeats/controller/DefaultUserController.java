package com.madbeats.controller;

import com.madbeats.entity.DefaultUser;
import com.madbeats.repository.DefaultUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            System.out.println("Email is required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is required");
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            System.out.println("Password is required");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is required");
        }

        if (user.getPassword().length() < 6) {
            System.out.println("Password must be at least 6 characters long");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password must be at least 6 characters long");
        }

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

    @GetMapping("/all_users")
    public ResponseEntity<List<DefaultUser>> getAllUsers() {
    	List<DefaultUser> users = defaultUserRepository.findAll();
    	if (users.isEmpty()) {
    		System.out.println("No users found");
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    	} else {
    		System.out.println("All users in data base: ");
    		for (DefaultUser user : users) {
    			System.out.println(user.getEmail());
    		}
    		return ResponseEntity.ok().body(users);
    	}
    }
    
    @DeleteMapping("/{userId}/delete_all_favourites")
    public ResponseEntity<String> deleteAllFavourites(@PathVariable String userId) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);
        if (optionalUser.isEmpty()) {
        	System.out.println("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        DefaultUser user = optionalUser.get();
        user.getFavouritesSpotList().clear();
        user.getFavouritesEventList().clear();
        defaultUserRepository.save(user);
        System.out.println("All user favourites deleted");
        return ResponseEntity.status(HttpStatus.OK).body("All user favourites deleted");
    }
    
    @DeleteMapping("/{userId}/delete_user")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);
        if (optionalUser.isEmpty()) {
        	System.out.println("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        defaultUserRepository.deleteById(userId);
        System.out.println("User deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }
    
    // Endpoint para modificar datos de usuario (email y/o password)
}
