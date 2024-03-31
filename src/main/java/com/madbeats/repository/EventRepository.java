package com.madbeats.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.madbeats.entity.Event;

public interface EventRepository extends MongoRepository<Event, String> {
	void deleteAll(); 
}