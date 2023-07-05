package com.kristofer.travelingapi.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import com.kristofer.travelingapi.dtos.PostDTO;
import com.kristofer.travelingapi.models.CommentModel;
import com.kristofer.travelingapi.models.PostModel;
import com.kristofer.travelingapi.models.UserModel;
import com.kristofer.travelingapi.services.CommentService;
import com.kristofer.travelingapi.services.PostService;
import com.kristofer.travelingapi.services.UserService;


@RestController
@RequestMapping(value="/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<PostDTO>> findAll(){
        List<PostModel> posts = postService.findAll();
        List<PostDTO> postsDTO = posts.stream().map(x-> new PostDTO(x))
        .collect(Collectors.toList());
        for(int i=0; i<postsDTO.size(); i++){
            UserModel user = userService.findById(posts.get(i).getAuthorId());
            postsDTO.get(i).setAuthor(new AuthorDTO(user));
        }
        return ResponseEntity.ok().body(postsDTO);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<PostDTO> findById(@PathVariable String id){
        PostModel post = postService.findById(id);
        PostDTO newPost = new PostDTO(post);
        UserModel user = userService.findById(post.getAuthorId());
        newPost.setAuthor(new AuthorDTO(user));
        return ResponseEntity.ok().body(newPost);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<String> Create(@RequestBody PostModel obj) {
        // Verify id with token JWT
        if(!this.verifyParams(1, obj).equals("pass")){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(this.verifyParams(1, obj));
        }else{
            PostModel post = new PostModel(obj);
            post = postService.insert(post);
            userService.addUserListPost(obj.getAuthorId(), post);
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
            PostModel post = postService.findById(id);
            post.setPhrase(obj.getPhrase());
            post.setImg(obj.getImg());
            post = postService.update(post);
            return ResponseEntity.noContent().build();
        }
        
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id){
        PostModel post  = postService.findById(id);
        userService.deletePostUser(post.getAuthorId(), post);
        this.deleteAllComments(id);
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}/comments", method=RequestMethod.GET)
    public ResponseEntity<List<CommentModel>> findComments(@PathVariable String id){
        PostModel post  = postService.findById(id);
        return ResponseEntity.ok().body(post.getComments());
    }

    public void deleteAllComments(String id){
        PostModel post = postService.findById(id);
        for(CommentModel comments : post.getComments()){
            commentService.delete(comments.getId());
        }
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
