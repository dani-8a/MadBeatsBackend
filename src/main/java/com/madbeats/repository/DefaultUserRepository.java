package com.madbeats.repository;

import com.madbeats.entity.DefaultUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DefaultUserRepository extends MongoRepository<DefaultUser, String> {
    Optional<DefaultUser> findByEmail(String email);
}
