package com.kristofer.travelingapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kristofer.travelingapi.models.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {
    UserModel findByEmailContaining(String email);
}
