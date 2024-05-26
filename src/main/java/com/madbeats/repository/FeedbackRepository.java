package com.madbeats.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.madbeats.entity.Feedback;

public interface FeedbackRepository extends MongoRepository<Feedback, String>{

}
