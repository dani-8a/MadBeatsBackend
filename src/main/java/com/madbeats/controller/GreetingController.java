package com.madbeats.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madbeats.repository.EventRepository;

@RestController
@RequestMapping("/v1")
public class GreetingController {
	EventRepository eventRepository;

	public GreetingController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

	@GetMapping("/hello")
    public String sayHello(@RequestParam String name) { // http://localhost:8080/v1/hello?name=MadBeats
        return "Hello, " + name + "!";
    }
/**
	@GetMapping("/events")
	public List<Event> getEvents(@RequestParam String musicCategory) { // http://localhost:8080/v1/events?musicCategory=Electronic
	    List<Event> filteredEvents = new ArrayList<>();
	    for (Event event : eventRepository.getEvents()) {
	        if (event.getMusicCategory().equals(musicCategory)) {
	            filteredEvents.add(event);
	        }
	    }
	    return filteredEvents;
	}
**/
}