package com.kristofer.travelingapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kristofer.travelingapi.dtos.UserDTO;
import com.kristofer.travelingapi.models.UserModel;
import com.kristofer.travelingapi.services.UserService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll(){
        
        List<UserModel> users = service.findAll();
        List<UserDTO> usersDTO = users.stream().map(x-> new UserDTO(x))
        .collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDTO);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        
        UserModel user = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody UserModel obj){
        //Bcrypt
        if(!this.verifyParams(obj).equals("pass")){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(this.verifyParams(obj));
        }else{
            UserModel user = new UserModel(obj.getId(),obj.getName(), obj.getEmail(), obj.getPassword(),
            obj.getImgUrl(), obj.getAt());
            obj = service.insert(user);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        
    }
    public String verifyParams(UserModel obj){
        // Validations
        // @Validation Email duplicated
        if(obj.getName() == null){
            return "Name: Name is required";
        }
        if(obj.getEmail() == null){
            return "Email: Email is required";
        }
        if(obj.getPassword() == null){
            return "Password: Password is required";
        }
        if(obj.getAt() == null){
            return "At: At is required";
        }
        return "pass";
    }
}
