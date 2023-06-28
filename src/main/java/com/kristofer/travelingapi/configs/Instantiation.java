package com.kristofer.travelingapi.configs;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.kristofer.travelingapi.models.PostModel;
import com.kristofer.travelingapi.models.UserModel;
import com.kristofer.travelingapi.repositories.PostRepository;
import com.kristofer.travelingapi.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        userRepository.deleteAll();
        postRepository.deleteAll();
        
        UserModel maria = new UserModel(null, "Maria Brown", 
        "maria@gmail.com", "teste123", "photo.png", "@maria",
        "photo.png", sdf.parse("21/03/2023"));

        UserModel alex = new UserModel(null, "Alex Green", 
        "alex@gmail.com", "teste123", "photo.png", "@alex", 
        "photo.png", sdf.parse("21/03/2023"));

        UserModel bob = new UserModel(null, "Bob Grey", 
        "bob@gmail.com", "teste123", "photo.png", "@bob", 
        "photo.png", sdf.parse("21/03/2023"));
        
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        maria.setFollowers(alex);
        alex.setFollowers(maria);
        bob.setFollowers(alex);
        bob.setFollowers(maria);
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        PostModel post1 = new PostModel(null, "My images", "img.png",
        sdf.parse("21/03/2023"), maria.getId());
        PostModel post2 = new PostModel(null, "Hello World", "img.png",
        sdf.parse("23/03/2023"), maria.getId());

        postRepository.saveAll(Arrays.asList(post1, post2));
        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
    
}
