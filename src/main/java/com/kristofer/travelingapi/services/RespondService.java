package com.kristofer.travelingapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristofer.travelingapi.models.RespondModel;
import com.kristofer.travelingapi.repositories.RespondRepository;
import com.kristofer.travelingapi.services.exceptions.ObjectNotFoundException;

@Service
public class RespondService {
    @Autowired
    RespondRepository repo;
    public List<RespondModel> findAll(){
        return repo.findAll();
    }

    public RespondModel findById(String id){
        Optional<RespondModel> obj = repo.findById(id);
        return obj.orElseThrow(
            ()-> new ObjectNotFoundException("Object with id " + id + " not found"));
    }

    public RespondModel insert(RespondModel obj){
        return repo.insert(obj);
    }

    public RespondModel update(RespondModel obj){
        return repo.save(obj);
    }

    public void delete(String id){
        this.findById(id);
        repo.deleteById(id);
    }
}
