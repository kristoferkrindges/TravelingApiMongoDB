package com.kristofer.travelingapi.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kristofer.travelingapi.dtos.AuthorDTO;

import com.kristofer.travelingapi.models.StorieModel;
import com.kristofer.travelingapi.models.UserModel;
import com.kristofer.travelingapi.services.StorieService;
import com.kristofer.travelingapi.services.UserService;

@RestController
@RequestMapping(value="/stories")
public class StorieController {
    @Autowired
    private StorieService storieService;
    @Autowired
    private UserService userService;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<StorieModel>> findAll(){
        List<StorieModel> posts = storieService.findAll();
        for(StorieModel item: posts){
            UserModel user = userService.findById(item.getAuthorId());
            item.setAuthor(new AuthorDTO(user));
        }
        return ResponseEntity.ok().body(posts);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<StorieModel> findById(@PathVariable String id){
        
        StorieModel post = storieService.findById(id);
        UserModel user = userService.findById(post.getAuthorId());
        post.setAuthor(new AuthorDTO(user));
        return ResponseEntity.ok().body(post);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<String> Create(@RequestBody StorieModel obj) {
        // Verify id with token JWT
        if(!this.verifyParams(obj).equals("pass")){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(this.verifyParams(obj));
        }else{
            StorieModel storie = new StorieModel(obj);
            storie = storieService.insert(storie);
            userService.addUserListStorie(obj.getAuthorId(), storie);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }

    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id){
        StorieModel storie  = storieService.findById(id);
        userService.deleteStorieUser(storie.getAuthorId(), storie);
        storieService.delete(id);
        return ResponseEntity.noContent().build();
    }

    public String verifyParams(StorieModel obj){
        if(obj.getDatepublic() == null){
            return "Date: Date is required";
        }
        if(obj.getAuthorId() == null){
            return "AuthorId: AuthorId is required";
        }

        if(obj.getVideo() == null){
            return "Video: Video is required";
        }
        
        return "pass";
    }
}
