package com.kristofer.travelingapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kristofer.travelingapi.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserController {
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<User>> findAll(){
        User kristofer = new User("1", "Kristofer Krindges", 
        "kristoferkrindges@gmail.com", "teste123", 
        "minhaimagem.png", "@KristoferRK");
        User samanta = new User("1", "Samanta Krindges", 
        "samantakrindges@gmail.com", "teste123", 
        "minhaimagem.png", "@SamantaRK");
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(kristofer, samanta));
        return ResponseEntity.ok().body(list);
    }
}
