package com.madbeats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.madbeats.entity.Spot;
import com.madbeats.repository.SpotRepository;

@RestController
@RequestMapping("/api/spots")
public class SpotController {

    @Autowired
    private SpotRepository spotRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Spot> getAllSpots(){
        List<Spot> spots = spotRepository.findAll();
        System.out.println("Total spots found: " + spots.size());
        for (Spot spot : spots) {
            System.out.println("Spot ID: " + spot.getIdSpot());
            System.out.println("Spot Name: " + spot.getNameSpot());
            System.out.println("Spot Address: " + spot.getAddressSpot());
        }
        return spots;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createSpot(@RequestBody Spot spot) {
        System.out.println("Received spot creation request:");
        System.out.println("ID: " + spot.getIdSpot());
        System.out.println("Name: " + spot.getNameSpot());
        System.out.println("Address: " + spot.getAddressSpot());

        try {
            spotRepository.save(spot);
            System.out.println("Spot saved successfully.");
        } catch (Exception e) {
            System.err.println("Error occurred while saving spot:");
            e.printStackTrace();
        }
    }
}

