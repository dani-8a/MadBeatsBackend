package com.madbeats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.madbeats")
public class MadbeatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MadbeatsApplication.class, args);
    }
}
