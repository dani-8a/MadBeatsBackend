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
}