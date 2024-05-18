package com.madbeats.repository;

import org.springframework.stereotype.Repository;

import com.madbeats.entity.DefaultUser;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface PromoterUserRepository extends MongoRepository <DefaultUser, String> {
	Optional <DefaultUser> findByEmail(String email);
}
