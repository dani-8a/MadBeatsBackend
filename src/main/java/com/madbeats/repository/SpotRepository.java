package com.madbeats.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.madbeats.entity.Spot;

public interface SpotRepository extends MongoRepository<Spot, String> {
}