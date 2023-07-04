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
import com.kristofer.travelingapi.models.RespondModel;
import com.kristofer.travelingapi.models.UserModel;
import com.kristofer.travelingapi.services.CommentService;
import com.kristofer.travelingapi.services.RespondService;
import com.kristofer.travelingapi.services.UserService;
@RestController
@RequestMapping(value="/respond")
public class RespondController {
    @Autowired
    private RespondService respondService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<RespondModel>> findAll(){
        List<RespondModel> responds = respondService.findAll();
        for(RespondModel item: responds){
            UserModel user = userService.findById(item.getAuthorId());
            item.setAuthor(new AuthorDTO(user));
        }
        return ResponseEntity.ok().body(responds);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<RespondModel> findById(@PathVariable String id){
        
        RespondModel responds = respondService.findById(id);
        UserModel user = userService.findById(responds.getAuthorId());
        responds.setAuthor(new AuthorDTO(user));
        return ResponseEntity.ok().body(responds);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<String> Create(@RequestBody RespondModel obj) {
        // Verify id with token JWT
        if(!this.verifyParams(obj).equals("pass")){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(this.verifyParams(obj));
        }else{
            RespondModel respond = new RespondModel(obj);
            respond = respondService.insert(respond);
            //commentService.addUserListRespond(obj.getAuthorId(), respond);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(obj.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }

    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<String> edit(@RequestBody RespondModel obj, 
    @PathVariable String id){
        if(!this.verifyParams(obj).equals("pass")){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(this.verifyParams(obj));
        }else{
            RespondModel respond = respondService.findById(id);
            respond.setPharase(obj.getPharase());
            respond = respondService.update(respond);
            return ResponseEntity.noContent().build();
        }
        
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id){
        RespondModel respond  = respondService.findById(id);
        commentService.deleteRespondInListComment(respond.getCommentId(), respond);
        respondService.delete(id);
        return ResponseEntity.noContent().build();
    }

    public String verifyParams(RespondModel obj){
        if(obj.getPharase() == null){
            return "Pharase: Pharase is required";
        }
        if(obj.getDatePost() == null){
            return "DatePost: DatePost is required";
        }
        if(obj.getAuthorId() == null){
            return "AuthorId: AuthorId is required";
        }
        return "pass";
    }

}
