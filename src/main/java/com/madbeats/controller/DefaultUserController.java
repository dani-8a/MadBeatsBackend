package com.madbeats.controller;

import com.madbeats.entity.DefaultUser;
import com.madbeats.entity.Event;
import com.madbeats.entity.Spot;
import com.madbeats.repository.DefaultUserRepository;
import com.madbeats.repository.EventRepository;
import com.madbeats.repository.SpotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/default_user")
public class DefaultUserController {
	
	@Autowired
    private final DefaultUserRepository defaultUserRepository;
	@Autowired
    private EventRepository eventRepository;
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
        System.out.println("User succesfully registered");
        return ResponseEntity.status(HttpStatus.CREATED).body("User succesfully registered");
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody DefaultUser user) {
        Optional<DefaultUser> existingUser = defaultUserRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            DefaultUser foundUser = existingUser.get();
            if (foundUser.getPassword().equals(user.getPassword())) {
                System.out.println("User logged: " + foundUser.getEmail());
                return ResponseEntity.ok("User logged");
            } else {
            	System.out.println("Wrong password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password");
            }
        } else {
        	System.out.println("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
 
    
    @PostMapping("/{userId}/add_event/{eventId}")
    public ResponseEntity<String> addEventToFavourites(@PathVariable String userId, @PathVariable String eventId) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);
        Optional<Event> optionalEvent = eventRepository.findById(eventId);


        if (optionalUser.isEmpty()) {
            String userNotFoundMessage = "User not found";
            System.out.println(userNotFoundMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFoundMessage);
        }

        if (optionalEvent.isEmpty()) {
            String eventNotFoundMessage = "Event not found";
            System.out.println(eventNotFoundMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(eventNotFoundMessage);
        }

        DefaultUser user = optionalUser.get();
        Event event = optionalEvent.get();

        // Verificar si el evento ya está en la lista de favoritos del usuario
        if (user.getFavouritesEventList().stream().anyMatch(e -> e.getIdEvent().equals(eventId))) {
            System.out.println("Event is already in your favourite list");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event is already in your favourite list");
        }

        // Agregar el evento a la lista de favoritos del usuario y guardar los cambios
        user.getFavouritesEventList().add(event);
        defaultUserRepository.save(user);
        System.out.println("Event added to your favourite list");
        return ResponseEntity.status(HttpStatus.OK).body("Event added to your favourite list");
    }
    
    @GetMapping("/{userId}/favourite_events")
    public ResponseEntity<List<Event>> getFavouriteEvents(@PathVariable String userId) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);

        if (optionalUser.isEmpty()) {
        	System.out.println("User not found");
            return ResponseEntity.notFound().build();
        }

        DefaultUser user = optionalUser.get();
        List<Event> favoriteEvents = user.getFavouritesEventList();

        // Imprimir el nombre de cada evento por consola
        System.out.println("User favourite events:");
        favoriteEvents.forEach(event -> System.out.println(event.getNameEvent()));

        return ResponseEntity.ok(favoriteEvents);
    }

    @DeleteMapping("/{userId}/delete_all_events")
    public ResponseEntity<String> deleteAllEventsFromFavorites(@PathVariable String userId) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);

        if (optionalUser.isEmpty()) {
        	System.out.println("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        DefaultUser user = optionalUser.get();
        
        // Eliminar todos los eventos de la lista de favoritos del usuario
        user.getFavouritesEventList().clear();
        defaultUserRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("All favourite events deleted");
    }
    
    @DeleteMapping("/{userId}/delete_event_favourites/{eventId}")
    public ResponseEntity<String> removeEventFromFavourites(@PathVariable String userId, @PathVariable String eventId) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalUser.isEmpty()) {
            String userNotFoundMessage = "User not found";
            System.out.println(userNotFoundMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFoundMessage);
        }

        if (optionalEvent.isEmpty()) {
            String eventNotFoundMessage = "Event not found";
            System.out.println(eventNotFoundMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(eventNotFoundMessage);
        }

        DefaultUser user = optionalUser.get();
        Event event = optionalEvent.get();

        List<Event> favoriteEvents = user.getFavouritesEventList();

        // Verificar si el evento está en la lista de favoritos del usuario
        if (!favoriteEvents.stream().anyMatch(e -> e.getIdEvent().equals(eventId))) {
        	System.out.println("This event is not in favourites");
            return ResponseEntity.badRequest().body("This event is not in favourites");
        }

        // Eliminar el evento de la lista de favoritos y guardar los cambios
        favoriteEvents.removeIf(e -> e.getIdEvent().equals(eventId));
        defaultUserRepository.save(user);
        System.out.println("Event deleted from favourites");
        return ResponseEntity.ok("Event deleted from favourites");
    }

}



