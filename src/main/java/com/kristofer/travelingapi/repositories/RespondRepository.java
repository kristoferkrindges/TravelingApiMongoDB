package com.kristofer.travelingapi.repositories;

import org.springframework.stereotype.Repository;

import com.kristofer.travelingapi.models.RespondModel;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface RespondRepository extends MongoRepository<RespondModel, String> {
    
}
