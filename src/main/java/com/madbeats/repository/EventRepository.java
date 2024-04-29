package com.madbeats.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.madbeats.entity.Event;
import com.madbeats.entity.Spot;

public interface EventRepository extends MongoRepository<Event, String> {
	void deleteAll();
}