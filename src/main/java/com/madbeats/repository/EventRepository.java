package com.madbeats.repository;

import com.madbeats.model.Event;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepository {

    private Map<String, Event> eventMap = new HashMap<>();
    
    public EventRepository() {
        addEvent();
    }

    public void addEvent() {
    	
        Event event1 = new Event(01, 01, "Mondo Disko", "Solomun", "01/03/2024", "23:45-06:00", 25.0, 18, "Electronic", "www.mondodisko.com", "Casual",
                "Sala Coco", "Calle Alcalá 20, Madrid", "wwww.salacoco.com");

        Event event2 = new Event(02, 02, "Mermelada", "Luca Lozano", "05/03/2024", "23:45-06:00", 20.0, 18, "Electronic", "www.clubmalasaña.com", "Casual",
                "Club Malasaña", "Calle San Vicente Ferrer 23, Madrid", "clubmalasaña.com");
        
        Event event3 = new Event(03, 03, "Ocho y Medio Club", "Smart", "17/03/2024", "23:45-06:00", 20.0, 18, "Rock", "www.ochoymedioclub.com", "Casual",
                "Sala But", "Calle Barceló 11, Madrid", "www.madridenvivo.com/sala/la-paqui/.com");
        
        Event event4 = new Event(04, 04, "Swag City", "Smart", "20/03/2024", "23:45-06:00", 20.0, 18, "Latin", "www.shokomadrid.com", "Casual",
                "Sala Shoko", "Calle Toledo 86, Madrid", "www.shokomadrid.com");
        
        Event event5 = new Event(05, 05, "Noches Teatro Kapital", "DJ's Residentes", "10/03/2024", "23:45-06:00", 25.0, 18, "Pop", "www.teatrokapital.com", "Casual",
                "Teatro Kapital", "Calle Atocha 86, Madrid", "www.teatrokapital.com");
        
        Event event6 = new Event(06, 06, "Noches Teatro Slava", "DJ's Residentes", "12/03/2024", "23:45-06:00", 20.0, 18, "Pop", "www.teatroeslava.com", "Casual",
                "Teatro Slava", "Calle Arenal 11, Madrid", "www.teatroeslava.com");
        
        eventMap.put("evento1", event1);
        eventMap.put("evento2", event2);
        eventMap.put("evento3", event3);
        eventMap.put("evento4", event4);
        eventMap.put("evento5", event5);
        eventMap.put("evento6", event6);
    }

    public void readEvent(String key) {
    	
        Event event = eventMap.get(key);
        if (event != null) {
            System.out.println("Evento encontrado: " + event.toString());
        } else {
            System.out.println("Evento no encontrado con la clave: " + key);
        }
    }

    public List<Event> getEvents() {
        return new ArrayList<>(eventMap.values());
    }

}
