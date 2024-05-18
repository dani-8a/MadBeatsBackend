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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya está registrado");
        }

        defaultUserRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
    }
    
    @PostMapping("/{userId}/add_event/{eventId}")
    public ResponseEntity<String> addEventToFavourites(@PathVariable String userId, @PathVariable String eventId) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalUser.isEmpty() || optionalEvent.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario o evento no encontrado");
        }

        DefaultUser user = optionalUser.get();
        Event event = optionalEvent.get();

        // Verificar si el evento ya está en la lista de favoritos del usuario
        if (user.getFavouritesEventList().stream().anyMatch(e -> e.getIdEvent().equals(eventId))) {
            System.out.println("El evento ya está en la lista de favoritos del usuario");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El evento ya está en la lista de favoritos del usuario");
        }

        // Agregar el evento a la lista de favoritos del usuario y guardar los cambios
        user.getFavouritesEventList().add(event);
        defaultUserRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("Evento agregado a la lista de favoritos del usuario");
    }
    
    @GetMapping("/{userId}/favourite_events")
    public ResponseEntity<List<Event>> getFavouriteEvents(@PathVariable String userId) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DefaultUser user = optionalUser.get();
        List<Event> favoriteEvents = user.getFavouritesEventList();

        // Imprimir el nombre de cada evento por consola
        System.out.println("Eventos favoritos del usuario:");
        favoriteEvents.forEach(event -> System.out.println(event.getNameEvent()));

        return ResponseEntity.ok(favoriteEvents);
    }

    @DeleteMapping("/{userId}/delete_all_events")
    public ResponseEntity<String> deleteAllEventsFromFavorites(@PathVariable String userId) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        DefaultUser user = optionalUser.get();
        
        // Eliminar todos los eventos de la lista de favoritos del usuario
        user.getFavouritesEventList().clear();
        defaultUserRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("Todos los eventos eliminados de la lista de favoritos del usuario");
    }
    
    @DeleteMapping("/{userId}/delete_event_favourites/{eventId}")
    public ResponseEntity<String> removeEventFromFavourites(@PathVariable String userId, @PathVariable String eventId) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalUser.isEmpty() || optionalEvent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DefaultUser user = optionalUser.get();
        Event event = optionalEvent.get();

        List<Event> favoriteEvents = user.getFavouritesEventList();

        // Verificar si el evento está en la lista de favoritos del usuario
        if (!favoriteEvents.stream().anyMatch(e -> e.getIdEvent().equals(eventId))) {
            return ResponseEntity.badRequest().body("El evento no está en la lista de favoritos del usuario");
        }

        // Eliminar el evento de la lista de favoritos y guardar los cambios
        favoriteEvents.removeIf(e -> e.getIdEvent().equals(eventId));
        defaultUserRepository.save(user);

        return ResponseEntity.ok("El evento ha sido eliminado de la lista de favoritos del usuario");
    }



}



