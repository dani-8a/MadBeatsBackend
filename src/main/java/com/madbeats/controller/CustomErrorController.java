package com.madbeats.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomErrorController {

    @ExceptionHandler(Exception.class)
    public String handleError() {
        System.out.println("error....");
        return "error";
    }
}
