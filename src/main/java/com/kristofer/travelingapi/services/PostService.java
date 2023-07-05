package com.kristofer.travelingapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristofer.travelingapi.models.CommentModel;
import com.kristofer.travelingapi.models.PostModel;
import com.kristofer.travelingapi.repositories.PostRepository;
import com.kristofer.travelingapi.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
    @Autowired
    private PostRepository repo;

    public List<PostModel> findAll(){
        return repo.findAll();
    }

    public PostModel findById(String id){
        Optional<PostModel> obj = repo.findById(id);
        return obj.orElseThrow(
            ()-> new ObjectNotFoundException("Object with id " + id + " not found"));
    }

    public PostModel insert(PostModel obj){
        return repo.insert(obj);
    }

    public PostModel update(PostModel obj){
        return repo.save(obj);
    }

    public void delete(String id){
        this.findById(id);
        repo.deleteById(id);
    }

    public void addCommentListPost(String postId, CommentModel obj){
        PostModel post = this.findById(postId);
        post.getComments().add(obj);
        repo.save(post);
    }

    public void removeCommentListPost(String postId, CommentModel obj){
        PostModel post = this.findById(postId);
        post.getComments().remove(obj);
        repo.save(post);
    }
}
