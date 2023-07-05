package com.kristofer.travelingapi.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
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
import com.kristofer.travelingapi.dtos.CommentDTO;
import com.kristofer.travelingapi.services.CommentService;
import com.kristofer.travelingapi.services.PostService;
import com.kristofer.travelingapi.services.RespondService;
import com.kristofer.travelingapi.services.UserService;
import com.kristofer.travelingapi.models.CommentModel;
import com.kristofer.travelingapi.models.PostModel;
import com.kristofer.travelingapi.models.RespondModel;
import com.kristofer.travelingapi.models.UserModel;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    RespondService respondService;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<CommentDTO>> findAll(){
        List<CommentModel> comments = commentService.findAll();
        List<CommentDTO> commentsDTO = comments.stream().map(x-> new CommentDTO(x))
        .collect(Collectors.toList());
        for(int i=0; i<commentsDTO.size(); i++){
            UserModel user = userService.findById(comments.get(i).getAuthorId());
            commentsDTO.get(i).setAuthor(new AuthorDTO(user));
        }
        return ResponseEntity.ok().body(commentsDTO);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<CommentDTO> findById(@PathVariable String id){
        CommentModel comment = commentService.findById(id);
        CommentDTO commentDTO = new CommentDTO(comment);
        UserModel user = userService.findById(comment.getAuthorId());
        commentDTO.setAuthor(new AuthorDTO(user));
        return ResponseEntity.ok().body(commentDTO);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<String> Create(@RequestBody CommentModel obj) {
        // Verify id with token JWT
        if(!this.verifyParams(2, obj).equals("pass")){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(this.verifyParams(2, obj));
        }else{
            CommentModel comment = new CommentModel(obj);
            postService.findById(obj.getPostId());
            userService.findById(obj.getAuthorId());
            comment = commentService.insert(comment);
            postService.addCommentListPost(obj.getPostId(), comment);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }

    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<String> edit(@RequestBody CommentModel obj, 
    @PathVariable String id){
        if(!this.verifyParams(1, obj).equals("pass")){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(this.verifyParams(1, obj));
        }else{
            CommentModel comment = commentService.findById(id);
            comment.setPharase(obj.getPharase());
            comment = commentService.update(comment);
            return ResponseEntity.noContent().build();
        }
        
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id){
        CommentModel comment  = commentService.findById(id);
        postService.removeCommentListPost(comment.getPostId(), comment);
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}/responds", method=RequestMethod.GET)
    public ResponseEntity<List<RespondModel>> findComments(@PathVariable String id){
        CommentModel comment  = commentService.findById(id);
        return ResponseEntity.ok().body(comment.getRespods());
    }

    public void deleteAllComments(String id){
        CommentModel comment = commentService.findById(id);
        for(RespondModel responds : comment.getRespods()){
            respondService.delete(responds.getId());
        }
    }

    public String verifyParams(int type, CommentModel obj){
        if(type == 1){
            if(obj.getPharase() == null){
                return "Pharase: Pharase is required";
            }
        }
        else{
            if(obj.getPharase() == null){
                return "Pharase: Pharase is required";
            }
            if(obj.getDatePost() == null){
                return "DatePost: DatePost is required";
            }
            if(obj.getPostId() == null){
                return "PostId: PostId is required";
            }
            if(obj.getAuthorId() == null){
                return "AuthorId: AuthorId is required";
            }
        }
        return "pass";
    }
}
