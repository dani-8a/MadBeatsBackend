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
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createEvent(@RequestBody Event event) {
        try {
        	System.out.println("JSON recibido en el backend: " + event.toString());
        	//String idSpot = event.get
            // Guardar el evento
            Event savedEvent = eventRepository.save(event);
            System.out.println("Event saved successfully.");

            // Obtener el ID del spot asociado al evento
            String spotId = savedEvent.getSpot().getIdSpot();

            // Buscar el spot en la base de datos utilizando el ID
            Spot spot = spotRepository.findById(spotId).orElse(null);

            // Verificar si se encontró el spot
            if (spot != null) {
                // Actualizar el listado de eventos en el spot
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
}
