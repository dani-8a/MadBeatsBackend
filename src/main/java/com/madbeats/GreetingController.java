package com.madbeats;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.madbeats.model.Event;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class GreetingController {
	
	@GetMapping("/hello")
    public String sayHello(@RequestParam String name) { // http://localhost:8081/v1/hello?name=MadBeats
        return "Hello, " + name + "!";
    }
	
	@GetMapping("/events")
    public List<Event> getEvents(@RequestParam String musicCategory) { // http://localhost:8080/v1/events?musicCategory=Electronic
		Event event1 = new Event(01, 01, "Mondo Disko", "Solomun", "01/03/2024", "23:45-06:00", 25.0, 18, "Electronic", "www.mondodisko.com", "Casual",
                "Sala Coco", "Calle Alcalá 20, Madrid", "wwww.salacoco.com");

        Event event2 = new Event(02, 02, "Mermelada", "Luca Lozano", "05/03/2024", "23:45-06:00", 20.0, 18, "Electronic", "clubmalasaña.com", "Casual",
                "Club Malasaña", "Calle San Vicente Ferrer 23, Madrid", "clubmalasaña.com");
        
        return List.of(event1,event2);
    }
}