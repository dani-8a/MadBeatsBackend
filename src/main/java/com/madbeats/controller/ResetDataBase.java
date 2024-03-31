package com.madbeats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.madbeats.repository.EventRepository;
import com.madbeats.repository.SpotRepository;

@RestController
@RequestMapping("/api/reset")
public class ResetDataBase {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SpotRepository spotRepository;

    @DeleteMapping
    public String resetDatabase() {
        eventRepository.deleteAll();
        spotRepository.deleteAll();
        return "Base de datos reiniciada correctamente.";
    }
}
