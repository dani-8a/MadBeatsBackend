package com.madbeats.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return eventRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createEvent(@RequestBody Event event) {
        try {
            System.out.println("JSON recibido en el backend: " + event.toString());
            Event savedEvent = eventRepository.save(event);
            System.out.println("Event saved successfully.");

            String spotId = savedEvent.getSpot().getIdSpot();
            Optional<Spot> optionalSpot = spotRepository.findById(spotId);
            if (optionalSpot.isPresent()) {
                Spot spot = optionalSpot.get();
                spot.getEvents().add(savedEvent);
                spotRepository.save(spot);
                System.out.println("Event added to the spot successfully.");
            } else {
                System.err.println("Spot not found for the event.");
            }
        } catch (Exception e) {
            System.err.println("Error occurred while saving event:");
            e.printStackTrace();
        }
    }
    
    @PutMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateEvent(@PathVariable String eventId, @RequestBody Event updatedEvent) {
        // Buscar el evento en la base de datos utilizando el ID
        Event event = eventRepository.findById(eventId).orElse(null);

        // Verificar si el evento existe
        if (event != null) {
            // Actualizar solo los campos proporcionados en la solicitud
        	if (updatedEvent.getIdEvent() != null) {
                event.setIdEvent(updatedEvent.getIdEvent());
            }
            if (updatedEvent.getNameEvent() != null) {
                event.setNameEvent(updatedEvent.getNameEvent());
            }
            if (updatedEvent.getArtist() != null) {
                event.setArtist(updatedEvent.getArtist());
            }
            if (updatedEvent.getDate() != null) {
                event.setDate(updatedEvent.getDate());
            }
            if (updatedEvent.getSchedule() != null) {
                event.setSchedule(updatedEvent.getSchedule());
            }
            if (updatedEvent.getPrice() != -1) {
                event.setPrice(updatedEvent.getPrice());
            }
            if (updatedEvent.getMinimumAge() != -1) {
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

            // Obtener el ID del spot asociado al evento
            String spotId = event.getSpot().getIdSpot();

            // Buscar el spot en la base de datos utilizando el ID
            Spot spot = spotRepository.findById(spotId).orElse(null);

            // Verificar si se encontró el spot
            if (spot != null) {
                // Actualizar el evento en el ArrayList de eventos del spot
                List<Event> events = spot.getEvents();
                for (int i = 0; i < events.size(); i++) {
                    if (events.get(i).getIdEvent().equals(eventId)) {
                        events.set(i, event);
                        spot.setEvents(events);
                        spotRepository.save(spot);
                        System.out.println("Event updated in the spot successfully.");
                        break;
                    }
                }
            } else {
                System.err.println("Spot not found for the event.");
            }
        } else {
            System.err.println("Event not found.");
        }
    }
    
    @DeleteMapping("/{idEvent}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable String idEvent) {
        // Buscar el evento en la base de datos utilizando el ID
        Event event = eventRepository.findById(idEvent).orElse(null);

        // Verificar si el evento existe
        if (event != null) {
            // Eliminar el evento de la base de datos
            eventRepository.delete(event);

            // Obtener el ID del spot asociado al evento
            String spotId = event.getSpot().getIdSpot();

            // Buscar el spot en la base de datos utilizando el ID
            Spot spot = spotRepository.findById(spotId).orElse(null);

            // Verificar si se encontró el spot
            if (spot != null) {
                // Eliminar el evento del listado de eventos en el spot
                spot.getEvents().remove(event);
                spotRepository.save(spot);
                System.out.println("Event removed from the spot successfully.");
            } else {
                System.err.println("Spot not found for the event.");
            }

            System.out.println("Event deleted successfully.");
        } else {
            System.err.println("Event not found.");
        }
    }
}
