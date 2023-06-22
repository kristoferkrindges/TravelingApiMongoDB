package com.kristofer.travelingapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kristofer.travelingapi.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmailContaining(String email);
}
