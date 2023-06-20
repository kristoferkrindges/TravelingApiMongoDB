package com.kristofer.travelingapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kristofer.travelingapi.dto.UserDTO;
import com.kristofer.travelingapi.models.User;
import com.kristofer.travelingapi.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll(){
        
        List<User> users = service.findAll();
        List<UserDTO> usersDTO = users.stream().map(x-> new UserDTO(x))
        .collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDTO);
    }
}
