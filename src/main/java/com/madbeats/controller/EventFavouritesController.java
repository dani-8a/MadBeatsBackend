package com.madbeats.controller;

import com.madbeats.entity.DefaultUser;
import com.madbeats.entity.Event;
import com.madbeats.repository.DefaultUserRepository;
import com.madbeats.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/default_user")
public class EventFavouritesController {

    @Autowired
    private DefaultUserRepository defaultUserRepository;

    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/{userId}/add_event/{eventId}")
    public ResponseEntity<String> addEventToFavourites(@PathVariable String userId, @PathVariable String eventId) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalUser.isEmpty()) {
            System.out.println("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (optionalEvent.isEmpty()) {
            System.out.println("Event not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }

        DefaultUser user = optionalUser.get();
        Event event = optionalEvent.get();

        if (user.getFavouritesEventList().stream().anyMatch(e -> e.getIdEvent().equals(eventId))) {
            System.out.println("Event is already in your favourite list");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Event is already in your favourite list");
        }

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
        user.getFavouritesEventList().clear();
        defaultUserRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("All favourite events deleted");
    }

    @DeleteMapping("/{userId}/delete_event_favourites/{eventId}")
    public ResponseEntity<String> removeEventFromFavourites(@PathVariable String userId, @PathVariable String eventId) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalUser.isEmpty()) {
            System.out.println("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (optionalEvent.isEmpty()) {
            System.out.println("Event not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }

        DefaultUser user = optionalUser.get();
        List<Event> favoriteEvents = user.getFavouritesEventList();

        if (!favoriteEvents.stream().anyMatch(e -> e.getIdEvent().equals(eventId))) {
            System.out.println("This event is not in favourites");
            return ResponseEntity.badRequest().body("This event is not in favourites");
        }

        favoriteEvents.removeIf(e -> e.getIdEvent().equals(eventId));
        defaultUserRepository.save(user);
        System.out.println("Event deleted from favourites");
        return ResponseEntity.ok("Event deleted from favourites");
    }
}
