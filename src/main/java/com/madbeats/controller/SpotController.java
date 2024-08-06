 	package com.madbeats.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.madbeats.entity.Event;
import com.madbeats.entity.Spot;
import com.madbeats.entity.SpotWithEventResponse;
import com.madbeats.repository.EventRepository;
import com.madbeats.repository.SpotRepository;

@RestController
@RequestMapping("/api/spots")
public class SpotController {

    @Autowired
    private SpotRepository spotRepository;
    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Spot> getAllSpots(){
        List<Spot> spots = spotRepository.findAll();
        System.out.println("Total spots found: " + spots.size());
        System.out.println("");
        for (Spot spot : spots) {
            System.out.println("Spot ID: " + spot.getIdSpot());
            System.out.println("Spot Name: " + spot.getNameSpot());
            System.out.println("Spot Address: " + spot.getAddressSpot());
            System.out.println("");
        }
        return spots;
    }
    
    @GetMapping("/{spotId}/events_associated")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SpotWithEventResponse> getSpotWithEvents(@PathVariable String spotId) {
        Optional<Spot> spotOptional = spotRepository.findById(spotId);
        if (spotOptional.isPresent()) {
            Spot spot = spotOptional.get();
            
            // Obtener todos los eventos asociados a ese spot
            List<Event> events = eventRepository.findBySpot(spot);
            
            System.out.println("Spot Information:");
            System.out.println("-----------------");
            System.out.println("Spot ID: " + spot.getIdSpot());
            System.out.println("Spot Name: " + spot.getNameSpot());
            System.out.println("Spot Address: " + spot.getAddressSpot());
            System.out.println("");
            System.out.println("Events Associated with Spot:");
            System.out.println("----------------------------");
            for (Event event : events) {
                System.out.println("Event ID: " + event.getIdEvent());
                System.out.println("Event Name: " + event.getNameEvent());
                System.out.println("");
            }  
            // Crear el objeto SpotWithEventResponse
            SpotWithEventResponse spotWithEventResponse = new SpotWithEventResponse(spot, events);
            
            // Devolver el objeto en la respuesta
            return ResponseEntity.ok(spotWithEventResponse);
        } else {
        	System.out.println("*** Spot not found ***");
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/spotsByMusicCategory/{musicCategory}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Spot>> getSpotsByMusicCategory(@PathVariable String musicCategory) {
        // Obtener todos los eventos con la categoría musical proporcionada
        List<Event> events = eventRepository.findByMusicCategory(musicCategory);
        
        // Crear un HashSet para almacenar los spots sin duplicados
        Set<Spot> uniqueSpots = new HashSet<>();
        
        // Agregar los spots asociados a los eventos de la categoria musical al HashSet
        for (Event event : events) {
            uniqueSpots.add(event.getSpot());
        }
        System.out.println(uniqueSpots.size()+ " spots with events of music category: " + musicCategory);
        System.out.println("");
        for (Spot spot : uniqueSpots) {
            System.out.println("Spot ID: " + spot.getIdSpot());
            System.out.println("Spot Name: " + spot.getNameSpot());
            System.out.println("Spot Address: " + spot.getAddressSpot());
            System.out.println("");
        }        
        // Convertir el HashSet en una lista
        List<Spot> spots = new ArrayList<>(uniqueSpots);
        
        // Devolver la lista de spots como respuesta
        return ResponseEntity.ok(spots);
    }
    
    @GetMapping("/spotsByEventDate/{day}/{month}/{year}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Spot>> getSpotsByEventDate(@PathVariable int day, @PathVariable int month, @PathVariable int year) {
        // Construir la fecha en el formato del repositorio de eventos
        String date = String.format("%02d/%02d/%04d", day, month, year);
    	
        // Obtener todos los eventos con la fecha proporcionada
        List<Event> events = eventRepository.findByDate(date);
        
        // Crear un conjunto para almacenar los spots sin duplicados
        Set<Spot> uniqueSpots = new HashSet<>();
        
        // Agregar los spots asociados a los eventos al conjunto
        for (Event event : events) {
            uniqueSpots.add(event.getSpot());
        }
        System.out.println(uniqueSpots.size()+" spots with events on date: " + date);
        System.out.println("");
        for (Spot spot : uniqueSpots) {
            System.out.println("Spot ID: " + spot.getIdSpot());
            System.out.println("Spot Name: " + spot.getNameSpot());
            System.out.println("Spot Address: " + spot.getAddressSpot());
            System.out.println("");
        }
        
        // Convertir el conjunto en una lista
        List<Spot> spots = new ArrayList<>(uniqueSpots);
        
        // Devolver la lista de spots como respuesta
        return ResponseEntity.ok(spots);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createSpot(@Valid @RequestBody Spot spot) {
        System.out.println("JSON recieved to create an event: " + spot.toString());
        System.out.println("");
        System.out.println("ID: " + spot.getIdSpot());
        System.out.println("Spot name: " + spot.getNameSpot());
        System.out.println("Address: " + spot.getAddressSpot());
        System.out.println("");

        try {
            spotRepository.save(spot);
            System.out.println("Spot created succesfully");
            System.out.println("");
            return ResponseEntity.ok("Spot created succesfully");
        } catch (Exception e) {
            System.err.println("Error creating the spot:");
            e.printStackTrace();
            System.out.println("");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating the spot");
        }
    }
    
    @PutMapping("/update_spot/{spotId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSpot(@PathVariable String spotId, @RequestBody Spot updatedSpot) {
        Spot spot = spotRepository.findById(spotId).orElse(null);

        if (spot != null) {
            // Actualizar solo los campos proporcionados en la solicitud
        	if (updatedSpot.getIdSpot() != null) {
                spot.setIdSpot(updatedSpot.getIdSpot());
            }
            if (updatedSpot.getNameSpot() != null) {
                spot.setNameSpot(updatedSpot.getNameSpot());
            }
            if (updatedSpot.getAddressSpot() != null) {
                spot.setAddressSpot(updatedSpot.getAddressSpot());
            }

            // Guardar el spot actualizado en la base de datos
            spotRepository.save(spot);
            System.out.println("Spot updated successfully");
            System.out.println("");
        } else {
            System.err.println("Spot not found");
            System.out.println("");
        }
    }

    
    @DeleteMapping("/delete_spot/{spotId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSpot(@PathVariable String spotId) {
        Spot spot = spotRepository.findById(spotId).orElse(null);

        if (spot != null) {
            // Eliminar el spot de la base de datos
            spotRepository.delete(spot);
            System.out.println("Spot deleted successfully");
            System.out.println("");
        } else {
            System.err.println("Spot not found");
            System.out.println("");
        }
    }
    
}

