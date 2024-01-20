package com.madbeats;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class GreetingController {
	
	@GetMapping("/hello")
    public String sayHello(@RequestParam String name) {
        return "Hello, jjj" + name + "!";
    }

}