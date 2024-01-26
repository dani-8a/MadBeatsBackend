package com.madbeats.controller;

import java.util.HashMap;
import java.util.Map;
import com.madbeats.model.Event;

public class EventHandler {

    private Map<String, Event> eventMap = new HashMap<>();

    public static void main(String[] args) {
    	
        EventHandler eventHandler = new EventHandler();
        eventHandler.addEvent();
        eventHandler.readEvent("evento1");
        eventHandler.readEvent("evento2");
    }

    public void addEvent() {
    	
        Event event1 = new Event(1, 101, "Concierto", "Artista1", "2024-01-30", "19:00", 50.0, 18, "Música", "http://example.com", "Casual",
                "Lugar1", "Dirección1", "http://spot1.com");

        Event event2 = new Event(2, 102, "Festival", "Artista2", "2024-02-15", "15:30", 75.0, 21, "Festival", "http://example.com/festival", "Casual",
                "Lugar2", "Dirección2", "http://spot2.com");

        eventMap.put("evento1", event1);
        eventMap.put("evento2", event2);
    }

    public void readEvent(String key) {
    	
        Event event = eventMap.get(key);
        if (event != null) {
            System.out.println("Evento encontrado: " + event);
        } else {
            System.out.println("Evento no encontrado con la clave: " + key);
        }
    }
}
