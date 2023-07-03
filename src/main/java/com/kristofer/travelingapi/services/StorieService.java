package com.kristofer.travelingapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristofer.travelingapi.models.StorieModel;
import com.kristofer.travelingapi.repositories.StorieRepository;
import com.kristofer.travelingapi.services.exceptions.ObjectNotFoundException;

@Service
public class StorieService {
    @Autowired
    StorieRepository repo;

    public List<StorieModel> findAll(){
        return repo.findAll();
    }

    public StorieModel findById(String id){
        Optional<StorieModel> obj = repo.findById(id);
        return obj.orElseThrow(
            ()-> new ObjectNotFoundException("Object with id " + id + " not found"));
    }

    public StorieModel insert(StorieModel obj){
        return repo.insert(obj);
    }

    public StorieModel update(StorieModel obj){
        return repo.save(obj);
    }

    public void delete(String id){
        this.findById(id);
        repo.deleteById(id);
    }
}
