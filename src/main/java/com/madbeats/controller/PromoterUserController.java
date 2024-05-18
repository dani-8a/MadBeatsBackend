package com.madbeats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.madbeats.entity.DefaultUser;
import com.madbeats.repository.DefaultUserRepository;


@RestController
public class PromoterUserController {
	@Autowired
	private DefaultUserRepository defaultUserRepository;


}
