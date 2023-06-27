package com.kristofer.travelingapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kristofer.travelingapi.models.PostModel;

@Repository
public interface PostRepository extends MongoRepository<PostModel, String>{
    
}
