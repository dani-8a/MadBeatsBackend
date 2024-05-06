package com.madbeats.controller;

import com.madbeats.entity.DefaultUser;
import com.madbeats.entity.Event;
import com.madbeats.entity.Spot;
import com.madbeats.entity.SpotWithEventResponse;
import com.madbeats.repository.DefaultUserRepository;
import com.madbeats.repository.EventRepository;
import com.madbeats.repository.SpotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/default_user")
public class DefaultUserController {
	
	@Autowired
    private final DefaultUserRepository defaultUserRepository;
	@Autowired
    private EventRepository eventRepository;
	@Autowired
    private SpotRepository spotRepository;
	@Autowired
	private SpotController spotController; 

    @Autowired
    public DefaultUserController(DefaultUserRepository defaultUserRepository) {
        this.defaultUserRepository = defaultUserRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody DefaultUser user) {
        Optional<DefaultUser> existingUser = defaultUserRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya está registrado");
        }

        defaultUserRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
    }

    @PostMapping("/{userId}/events/{eventId}/addToFavourites")
    public ResponseEntity<String> addEventToFavourites(
            @PathVariable String userId,
            @PathVariable String eventId
    ) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalUser.isEmpty() || optionalEvent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DefaultUser user = optionalUser.get();
        Event event = optionalEvent.get();
        user.getFavouritesEventList().add(event);

        defaultUserRepository.save(user);
        return ResponseEntity.ok("Evento agregado a favoritos exitosamente");
    }


    @PostMapping("/{userId}/spots/{spotId}/addToFavourites")
    public ResponseEntity<String> addSpotToFavourites(
            @PathVariable String userId,
            @PathVariable String spotId
    ) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);
        Optional<Spot> optionalSpot = spotRepository.findById(spotId);

        if (optionalUser.isEmpty() || optionalSpot.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DefaultUser user = optionalUser.get();
        Spot spot = optionalSpot.get();

        // Utiliza el método getSpotWithEvents del SpotController para obtener SpotWithEventResponse
        ResponseEntity<SpotWithEventResponse> response = spotController.getSpotWithEvents(spotId);
        if (response.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.notFound().build();
        }
        SpotWithEventResponse spotResponse = response.getBody();

        // Agregar el SpotWithEventResponse a la lista de favoritos del usuario
        user.getFavouritesSpotList().add(spotResponse);

        defaultUserRepository.save(user);
        return ResponseEntity.ok("Spot agregado a favoritos exitosamente");
    }
    
    @DeleteMapping("/{userId}/events/clearFavourites")
    public ResponseEntity<String> clearFavouritesEvents(
            @PathVariable String userId
    ) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DefaultUser user = optionalUser.get();
        user.getFavouritesEventList().clear();
        defaultUserRepository.save(user);

        return ResponseEntity.ok("Contenido de favouritesEventList eliminado exitosamente");
    }

    @DeleteMapping("/{userId}/spots/clearFavourites")
    public ResponseEntity<String> clearFavouritesSpots(
            @PathVariable String userId
    ) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DefaultUser user = optionalUser.get();
        user.getFavouritesSpotList().clear();
        defaultUserRepository.save(user);

        return ResponseEntity.ok("Contenido de favouritesSpotList eliminado exitosamente");
    }

}



