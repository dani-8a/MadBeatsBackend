package com.madbeats.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
        for (Spot spot : spots) {
            System.out.println("Spot ID: " + spot.getIdSpot());
            System.out.println("Spot Name: " + spot.getNameSpot());
            System.out.println("Spot Address: " + spot.getAddressSpot());
        }
        return spots;
    }
    
    @GetMapping("/{spotId}/events")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SpotWithEventResponse> getSpotWithEvents(@PathVariable String spotId) {
        // Buscar el spot por su ID
        Optional<Spot> spotOptional = spotRepository.findById(spotId);
        if (spotOptional.isPresent()) {
            Spot spot = spotOptional.get();
            
            // Obtener todos los eventos asociados a ese spot
            List<Event> events = eventRepository.findBySpot(spot);
            
            // Imprimir información del spot
            System.out.println("Spot Information:");
            System.out.println("-----------------");
            System.out.println("Spot ID: " + spot.getIdSpot());
            System.out.println("Spot Name: " + spot.getNameSpot());
            System.out.println("Spot Address: " + spot.getAddressSpot());
            System.out.println("");
            
            // Imprimir información de los eventos asociados
            System.out.println("Events Associated with Spot:");
            System.out.println("----------------------------");
            for (Event event : events) {
                System.out.println("Event ID: " + event.getIdEvent());
                System.out.println("Event Name: " + event.getNameEvent());
                System.out.println("");
                // Imprime más información si lo deseas
            }
            
            // Crear el objeto SpotWithEventResponse
            SpotWithEventResponse spotWithEventResponse = new SpotWithEventResponse(spot, events);
            
            // Devolver el objeto en la respuesta
            return ResponseEntity.ok(spotWithEventResponse);
        } else {
            // Si el spot no se encuentra, devolver una respuesta de error 404
        	System.out.println("*** Spot not found ***");
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/spotsByMusicCategory/{musicCategory}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<Spot>> getSpotsByMusicCategory(@PathVariable String musicCategory) {
        // Obtener todos los eventos con la categoría musical proporcionada
        List<Event> events = eventRepository.findByMusicCategory(musicCategory);
        
        // Crear un HashSet para almacenar los spots sin duplicados
        Set<Spot> uniqueSpots = new HashSet<>();
        
        // Agregar los spots asociados a los eventos de la categoria musical al HashSet
        for (Event event : events) {
            uniqueSpots.add(event.getSpot());
        }
        
        // Imprimir la información de los spots
        System.out.println(uniqueSpots.size()+ " spots with events of music category: " + musicCategory);
        for (Spot spot : uniqueSpots) {
            System.out.println("Spot ID: " + spot.getIdSpot());
            System.out.println("Spot Name: " + spot.getNameSpot());
            System.out.println("Spot Address: " + spot.getAddressSpot());
        }
        
        // Convertir el HashSet en una lista
        //List<Spot> spots = new ArrayList<>(uniqueSpots);
        
        // Devolver la lista de spots como respuesta
        return ResponseEntity.ok(uniqueSpots);
    }
    
    @GetMapping("/spotsByEventDate/{day}/{month}/{year}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<Spot>> getSpotsByEventDate(@PathVariable int day, @PathVariable int month, @PathVariable int year) {
        // Construir la fecha en el formato esperado por el repositorio de eventos
        String date = String.format("%02d/%02d/%04d", day, month, year);
    	
        // Obtener todos los eventos con la fecha proporcionada
        List<Event> events = eventRepository.findByDate(date);
        
        // Crear un conjunto para almacenar los spots sin duplicados
        Set<Spot> uniqueSpots = new HashSet<>();
        
        // Agregar los spots asociados a los eventos al conjunto
        for (Event event : events) {
            uniqueSpots.add(event.getSpot());
        }
        
        // Imprimir la información de los spots
        System.out.println(uniqueSpots.size()+" spots with events on date: " + date);
        for (Spot spot : uniqueSpots) {
            System.out.println("Spot ID: " + spot.getIdSpot());
            System.out.println("Spot Name: " + spot.getNameSpot());
            System.out.println("Spot Address: " + spot.getAddressSpot());
        }
        
        // Convertir el conjunto en una lista
        //List<Spot> spots = new ArrayList<>(uniqueSpots);
        
        // Devolver la lista de spots como respuesta
        return ResponseEntity.ok(uniqueSpots);
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createSpot(@Valid @RequestBody Spot spot) {
        System.out.println("Solicitud de creación de lugar recibida:");
        System.out.println("ID: " + spot.getIdSpot());
        System.out.println("Nombre: " + spot.getNameSpot());
        System.out.println("Dirección: " + spot.getAddressSpot());

        try {
            spotRepository.save(spot);
            System.out.println("Lugar guardado correctamente.");
            return ResponseEntity.ok("Lugar creado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al guardar el lugar:");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el lugar.");
        }
    }
    
    @PutMapping("/{spotId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSpot(@PathVariable String spotId, @RequestBody Spot updatedSpot) {
        // Buscar el spot en la base de datos utilizando el ID
        Spot spot = spotRepository.findById(spotId).orElse(null);

        // Verificar si el spot existe
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
            System.out.println("Spot updated successfully.");
        } else {
            System.err.println("Spot not found.");
        }
    }

    
    @DeleteMapping("/{spotId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSpot(@PathVariable String spotId) {
        // Buscar el spot en la base de datos utilizando el ID
        Spot spot = spotRepository.findById(spotId).orElse(null);

        // Verificar si el spot existe
        if (spot != null) {
            // Eliminar el spot de la base de datos
            spotRepository.delete(spot);
            System.out.println("Spot deleted successfully.");
        } else {
            System.err.println("Spot not found.");
        }
    }
/**
    @DeleteMapping("/{spotId}/events")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllEventsInSpot(@PathVariable String spotId) {
        // Buscar el spot en la base de datos utilizando el ID
        Spot spot = spotRepository.findById(spotId).orElse(null);

        // Verificar si el spot existe
        if (spot != null) {
            // Eliminar todos los eventos del spot
            spot.getEvents().clear();
            spotRepository.save(spot);
            System.out.println("Events in spot deleted successfully.");
        } else {
            System.err.println("Spot not found.");
        }
    }
 
    @DeleteMapping("/{spotId}/events/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSelectedEventInSpot(@PathVariable String spotId, @PathVariable String eventId) {
        // Buscar el spot en la base de datos utilizando el ID
        Spot spot = spotRepository.findById(spotId).orElse(null);

        // Verificar si el spot existe
        if (spot != null) {
            // Buscar el evento en la lista de eventos del spot
            Event eventToDelete = null;
            for (Event event : spot.getEvents()) {
                if (event.getIdEvent().equals(eventId)) {
                    eventToDelete = event;
                    break;
                }
            }

            // Verificar si se encontró el evento
            if (eventToDelete != null) {
                // Eliminar el evento de la lista de eventos del spot
                spot.getEvents().remove(eventToDelete);
                spotRepository.save(spot);
                System.out.println("Event deleted from spot successfully.");
            } else {
                System.err.println("Event not found in the spot.");
            }
        } else {
            System.err.println("Spot not found.");
        }
    }**/
}

