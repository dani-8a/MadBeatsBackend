package com.madbeats.controller;

import java.util.List;
import java.util.Optional;

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
import com.madbeats.repository.EventRepository;
import com.madbeats.repository.SpotRepository;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SpotRepository spotRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();

        System.out.println("Total events found: " + events.size());
        for (Event event : events) {
            System.out.println("Event ID: " + event.getIdEvent());
            System.out.println("Event Name: " + event.getNameEvent());
            // Verificar si el evento tiene un spot asociado
            if (event.getSpot() != null) {
                System.out.println("Spot ID: " + event.getSpot().getIdSpot());
                System.out.println("Spot Name: " + event.getSpot().getNameSpot());
                System.out.println("Spot Address: " + event.getSpot().getAddressSpot());
            } else {
                System.out.println("No Spot associated with Event");
            }
        }

        return events;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createEvent(@Valid @RequestBody Event event) {
        try {
            System.out.println("JSON recibido en el backend para crear evento: " + event.toString());
            
            Event savedEvent = eventRepository.save(event);
            System.out.println("Evento guardado correctamente.");
            return ResponseEntity.status(HttpStatus.CREATED).body("Evento guardado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al guardar el evento:");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el evento.");
        }
    }
    
    @PutMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateEvent(@PathVariable String eventId, @RequestBody Event updatedEvent) {
        try {
            // Buscar el evento en la base de datos utilizando el ID
            Event event = eventRepository.findById(eventId).orElse(null);

            // Verificar si el evento existe
            if (event != null) {
                // Actualizar solo los campos proporcionados en la solicitud
                if (updatedEvent.getNameEvent() != null) {
                    event.setNameEvent(updatedEvent.getNameEvent());
                }
                if (updatedEvent.getArtists() != null) {
                    event.setArtists(updatedEvent.getArtists());
                }
                if (updatedEvent.getDate() != null) {
                    event.setDate(updatedEvent.getDate());
                }
                if (updatedEvent.getSchedule() != null) {
                    event.setSchedule(updatedEvent.getSchedule());
                }
                if (updatedEvent.getPrice() != 0) {
                    event.setPrice(updatedEvent.getPrice());
                }
                if (updatedEvent.getMinimumAge() != 0) {
                    event.setMinimumAge(updatedEvent.getMinimumAge());
                }
                if (updatedEvent.getMusicCategory() != null) {
                    event.setMusicCategory(updatedEvent.getMusicCategory());
                }
                if (updatedEvent.getUrlEvent() != null) {
                    event.setUrlEvent(updatedEvent.getUrlEvent());
                }
                if (updatedEvent.getDressCode() != null) {
                    event.setDressCode(updatedEvent.getDressCode());
                }

                // Guardar el evento actualizado en la base de datos
                eventRepository.save(event);
                System.out.println("Event updated successfully.");

                return ResponseEntity.ok().body("Event updated successfully.");
            } else {
                System.err.println("Event not found.");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("Error updating event:");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating event.");
        }
    }

    
    @DeleteMapping("/{idEvent}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteEvent(@PathVariable String idEvent) {
        try {
            // Buscar el evento en la base de datos utilizando el ID
            Event event = eventRepository.findById(idEvent).orElse(null);

            // Verificar si el evento existe
            if (event != null) {
                // Eliminar el evento de la base de datos
                eventRepository.delete(event);

                System.out.println("Event deleted successfully.");
                return ResponseEntity.ok().body("Event deleted successfully.");
            } else {
                System.err.println("Event not found.");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.err.println("Error deleting event:");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting event.");
        }
    }

}
