package com.kristofer.travelingapi.services;

import java.util.List;
import java.util.Optional;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.kristofer.travelingapi.models.User;
import com.kristofer.travelingapi.repositories.UserRepository;
import com.kristofer.travelingapi.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll(){
        return repo.findAll();
    }

    public User findById(String id){
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(
            ()-> new ObjectNotFoundException("Object with id " + id + " not found"));
    }
    public User insert(User obj){
        return repo.insert(obj);
    }

    public User findByEmail(String email){
        return repo.findByEmailContaining(email);
    }
}
