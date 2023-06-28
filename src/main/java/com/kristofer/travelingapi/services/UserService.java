package com.kristofer.travelingapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristofer.travelingapi.models.PostModel;
import com.kristofer.travelingapi.models.UserModel;
import com.kristofer.travelingapi.repositories.UserRepository;
import com.kristofer.travelingapi.services.exceptions.ObjectAlreadyExistsException;
import com.kristofer.travelingapi.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<UserModel> findAll(){
        return repo.findAll();
    }

    public UserModel findById(String id){
        Optional<UserModel> obj = repo.findById(id);
        return obj.orElseThrow(
            ()-> new ObjectNotFoundException("Object with id " + id + " not found"));
    }
    public UserModel insert(UserModel obj){
        this.verifyEmailExists(obj.getEmail());
        this.verifyAtExists(obj.getAt());
        return repo.insert(obj);
    }

    public UserModel update(UserModel obj){
        UserModel newObj = findById(obj.getId());
        if(!obj.getEmail().equals(newObj.getEmail())){
            this.verifyEmailExists(obj.getEmail());
        }
        if(!obj.getAt().equals(newObj.getAt())){
            this.verifyAtExists(obj.getAt());
        }
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(String id){
        this.findById(id);
        repo.deleteById(id);
    }

    private void updateData(UserModel newObj, UserModel obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
        newObj.setPhoto(obj.getPhoto());
        newObj.setAt(obj.getAt());
    }

    public void addUserListPost(String id, PostModel obj){
        UserModel user = this.findById(id);
        user.getPosts().add(obj);
        repo.save(user);
    }

    public void deletePostUser(String userId, PostModel post){
        UserModel user = this.findById(userId);
        System.out.println("Passou dps de procurar o id do user");
        user.getPosts().remove(post);
        repo.save(user);
    }

    private void verifyEmailExists(String email){
        if(this.findByEmail(email) != null){
            throw new ObjectAlreadyExistsException(
                "Email already registered in the system");
        }
    }

    private void verifyAtExists(String at){
        if(this.findByAt(at) != null){
            throw new ObjectAlreadyExistsException(
                "At already registered in the system");
        }
    }

    private UserModel findByEmail(String email){
        return repo.findByEmailContaining(email);
    }

    private UserModel findByAt(String at){
        return repo.findByAtContaining(at);
    }

}
