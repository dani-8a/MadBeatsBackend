package com.madbeats.controller;

import java.util.HashMap;
import java.util.Map;
import com.madbeats.model.Event;

public class EventHandler {

    private Map<String, Event> eventMap = new HashMap<>();

    public void addEvent() {
    	
        Event event1 = new Event(01, 01, "Mondo Disko", "Solomun", "01/03/2024", "23:45-06:00", 25.0, 18, "Electronic", "www.mondodisko.com", "Casual",
                "Sala Coco", "Calle Alcalá 20, Madrid", "wwww.salacoco.com");

        Event event2 = new Event(02, 02, "Mermelada", "Luca Lozano", "05/03/2024", "23:45-06:00", 20.0, 18, "Electronic", "clubmalasaña.com", "Casual",
                "Club Malasaña", "Calle San Vicente Ferrer 23, Madrid", "clubmalasaña.com");

        eventMap.put("evento1", event1);
        eventMap.put("evento2", event2);
    }

    public void readEvent(String key) {
    	
        Event event = eventMap.get(key);
        if (event != null) {
            System.out.println("Evento encontrado: " + event.toString());
        } else {
            System.out.println("Evento no encontrado con la clave: " + key);
        }
    }
}
