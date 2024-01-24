package com.madbeats.controller;

import com.madbeats.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1/events")
public class EventController {

	private List<Event> eventList = cargarEventosDesdeJson();

    @GetMapping
    public List<Event> getAllEvents() {
        return eventList;
    }
    
    private List<Event> cargarEventosDesdeJson() {
        try {
            
            Resource resource = new ClassPathResource("C:\\Users\\Balles\\Documents\\GitHub\\MadBeatsBackend\\src\\main\\resources\\static");
            InputStream inputStream = resource.getInputStream();

            // Utilizar Jackson para deserializar el JSON a una lista de eventos
            ObjectMapper objectMapper = new ObjectMapper();
            List<Event> eventos = objectMapper.readValue(inputStream, new TypeReference<List<Event>>() {});

            return eventos;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList(); // Retorna una lista vacía en caso de error
        }
    }

}
