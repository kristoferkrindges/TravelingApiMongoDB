package com.kristofer.travelingapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kristofer.travelingapi.dtos.AuthenticationDTO;
import com.kristofer.travelingapi.dtos.RegisterUserDTO;
import com.kristofer.travelingapi.models.UserModel;
import com.kristofer.travelingapi.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        System.out.println("Quebra");
        return ResponseEntity.ok().build();
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterUserDTO data){
        if(this.userRepository.findByEmailContaining(data.email()) != null){
            return ResponseEntity.badRequest().build();
        }
        if(this.userRepository.findByAtContaining(data.at()) != null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserModel newUser = new UserModel(data.email(), data.name(), data.at(), data.role(),
        data.birthdate(), encryptedPassword);

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();

    }
}
