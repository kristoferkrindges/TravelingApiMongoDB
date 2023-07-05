package com.kristofer.travelingapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristofer.travelingapi.models.CommentModel;
import com.kristofer.travelingapi.models.RespondModel;
import com.kristofer.travelingapi.repositories.CommentRepository;
import com.kristofer.travelingapi.services.exceptions.ObjectNotFoundException;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repo;

    public List<CommentModel> findAll(){
        return repo.findAll();
    }

    public CommentModel findById(String id){
        Optional<CommentModel> obj = repo.findById(id);
        return obj.orElseThrow(
            ()-> new ObjectNotFoundException("Object with id " + id + " not found"));
    }

    public CommentModel insert(CommentModel obj){
        return repo.insert(obj);
    }

    public CommentModel update(CommentModel obj){
        return repo.save(obj);
    }

    public void delete(String id){
        this.findById(id);
        repo.deleteById(id);
    }

    public void addRespondCommentList(String commentId, RespondModel obj){
        CommentModel comment = this.findById(commentId);
        comment.getRespods().add(obj);
        repo.save(comment);
    }
    public void deleteRespondInListComment(String commentId, RespondModel obj){
        CommentModel comment = this.findById(commentId);
        comment.getRespods().remove(obj);
        repo.save(comment);
    }
}
