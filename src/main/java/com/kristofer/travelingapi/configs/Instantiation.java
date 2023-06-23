package com.kristofer.travelingapi.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.kristofer.travelingapi.models.UserModel;
import com.kristofer.travelingapi.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        UserModel maria = new UserModel(null, "Maria Brown", "maria@gmail.com", 
        "teste123", "photo.png", "@maria");
        UserModel alex = new UserModel(null, "Alex Green", "alex@gmail.com", 
        "teste123", "photo.png", "@alex");
        UserModel bob = new UserModel(null, "Bob Grey", "bob@gmail.com", 
        "teste123", "photo.png", "@bob");

        // maria.setFollowers(alex);
        // alex.setFollowers(maria);
        // bob.setFollowers(alex);
        // bob.setFollowers(maria);

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
    }
    
}
