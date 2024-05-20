package com.madbeats.controller;

import com.madbeats.entity.DefaultUser;
import com.madbeats.entity.Spot;
import com.madbeats.repository.DefaultUserRepository;
import com.madbeats.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/default_user")
public class SpotFavouritesController {

    @Autowired
    private DefaultUserRepository defaultUserRepository;

    @Autowired
    private SpotRepository spotRepository;

    @PostMapping("/{userId}/add_spot/{spotId}")
    public ResponseEntity<String> addSpotToFavourites(@PathVariable String userId, @PathVariable String spotId) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);
        Optional<Spot> optionalSpot = spotRepository.findById(spotId);

        if (optionalUser.isEmpty()) {
            System.out.println("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (optionalSpot.isEmpty()) {
            System.out.println("Spot not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Spot not found");
        }

        DefaultUser user = optionalUser.get();
        Spot spot = optionalSpot.get();

        if (user.getFavouritesSpotList().stream().anyMatch(e -> e.getIdSpot().equals(spotId))) {
            System.out.println("Spot is already in your favourite list");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Spot is already in your favourite list");
        }

        user.getFavouritesSpotList().add(spot);
        defaultUserRepository.save(user);
        System.out.println("Spot added to your favourite list");
        return ResponseEntity.status(HttpStatus.OK).body("Spot added to your favourite list");
    }

    @GetMapping("/{userId}/favourite_spots")
    public ResponseEntity<List<Spot>> getFavouriteSpots(@PathVariable String userId) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            System.out.println("User not found");
            return ResponseEntity.notFound().build();
        }

        DefaultUser user = optionalUser.get();
        List<Spot> favoriteSpots = user.getFavouritesSpotList();

        System.out.println("User favourite spots:");
        favoriteSpots.forEach(spot -> System.out.println(spot.getNameSpot()));

        return ResponseEntity.ok(favoriteSpots);
    }

    @DeleteMapping("/{userId}/delete_all_spots")
    public ResponseEntity<String> deleteAllSpotsFromFavorites(@PathVariable String userId) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            System.out.println("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        DefaultUser user = optionalUser.get();
        user.getFavouritesSpotList().clear();
        defaultUserRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("All favourite spots deleted");
    }

    @DeleteMapping("/{userId}/delete_spot_favourites/{spotId}")
    public ResponseEntity<String> removeSpotFromFavourites(@PathVariable String userId, @PathVariable String spotId) {
        Optional<DefaultUser> optionalUser = defaultUserRepository.findById(userId);
        Optional<Spot> optionalSpot = spotRepository.findById(spotId);

        if (optionalUser.isEmpty()) {
            System.out.println("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (optionalSpot.isEmpty()) {
            System.out.println("Spot not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Spot not found");
        }

        DefaultUser user = optionalUser.get();
        List<Spot> favoriteSpots = user.getFavouritesSpotList();

        if (!favoriteSpots.stream().anyMatch(e -> e.getIdSpot().equals(spotId))) {
            System.out.println("This spot is not in favourites");
            return ResponseEntity.badRequest().body("This spot is not in favourites");
        }

        favoriteSpots.removeIf(e -> e.getIdSpot().equals(spotId));
        defaultUserRepository.save(user);
        System.out.println("Spot deleted from favourites");
        return ResponseEntity.ok("Spot deleted from favourites");
    }
}
