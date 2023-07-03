package com.kristofer.travelingapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kristofer.travelingapi.models.StorieModel;

@Repository
public interface StorieRepository extends MongoRepository<StorieModel, String> {
    
}
