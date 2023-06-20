package com.kristofer.travelingapi.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.kristofer.travelingapi.models.User;
import com.kristofer.travelingapi.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com", 
        "teste123", "photo.png", "@maria");
        User alex = new User(null, "Alex Green", "alex@gmail.com", 
        "teste123", "photo.png", "@alex");
        User bob = new User(null, "Bob Grey", "bob@gmail.com", 
        "teste123", "photo.png", "@bob");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
    }
    
}
