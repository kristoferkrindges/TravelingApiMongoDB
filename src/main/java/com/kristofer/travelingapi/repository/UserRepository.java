package com.kristofer.travelingapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kristofer.travelingapi.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    
}
