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
import com.kristofer.travelingapi.models.PostModel;
import com.kristofer.travelingapi.models.UserModel;
import com.kristofer.travelingapi.services.PostService;
import com.kristofer.travelingapi.services.UserService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value="/posts")
public class PostController {
    @Autowired
    private PostService service;

    @Autowired
    private UserService serviceUser;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<PostModel>> findAll(){
        List<PostModel> posts = service.findAll();
        for(PostModel item: posts){
            UserModel user = serviceUser.findById(item.getAuthorId());
            item.setAuthor(new AuthorDTO(user));
        }
        return ResponseEntity.ok().body(posts);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<PostModel> findById(@PathVariable String id){
        
        PostModel post = service.findById(id);
        UserModel user = serviceUser.findById(post.getAuthorId());
        post.setAuthor(new AuthorDTO(user));
        return ResponseEntity.ok().body(post);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<String> Create(@RequestBody PostModel obj) {
        // Verify id with token JWT
        if(!this.verifyParams(1, obj).equals("pass")){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(this.verifyParams(1, obj));
        }else{
            PostModel post = new PostModel(obj);
            post = service.insert(post);
            serviceUser.addUserListPost(obj.getAuthorId(), post);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }

    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<String> edit(@RequestBody PostModel obj, 
    @PathVariable String id){
        if(!this.verifyParams(2, obj).equals("pass")){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(this.verifyParams(2, obj));
        }else{
            PostModel post = service.findById(id);
            post.setPhrase(obj.getPhrase());
            post.setImg(obj.getImg());
            post = service.update(post);
            return ResponseEntity.noContent().build();
        }
        
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id){
        PostModel post  = service.findById(id);
        serviceUser.deletePostUser(post.getAuthorId(), post);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    public String verifyParams(int type, PostModel obj){
        if(type == 1){
            if(obj.getDate() == null){
                return "Date: Date is required";
            }
            if(obj.getAuthorId() == null){
                return "AuthorId: AuthorId is required";
            }
        }else{
            if(obj.getPhrase() == null){
                return "Phrase: Phrase is required";
            }
            if(obj.getImg() == null){
                return "Img: Img is required";
            }
        }
        
        return "pass";
    }
    
}
