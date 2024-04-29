package com.madbeats.repository;
/**
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.madbeats.entity.User;

public interface UserRepository extends MongoRepository<User, String>{
	Optional<User> findByUsername (String username);
	boolean existsByUsername (String username);
}
**/