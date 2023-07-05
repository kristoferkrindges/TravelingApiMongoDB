package com.kristofer.travelingapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kristofer.travelingapi.models.CommentModel;

@Repository
public interface CommentRepository extends MongoRepository<CommentModel,  String> {
    
}
