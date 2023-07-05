package com.kristofer.travelingapi.dtos;

import java.io.Serializable;
import java.util.Date;

import com.kristofer.travelingapi.models.CommentModel;

public class CommentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String pharase;
    private Date datePost;
    private int likes;
    private int respond;
    private AuthorDTO author;
    private String postId;
    
    public CommentDTO(CommentModel obj) {
        this.id = obj.getId();
        this.pharase = obj.getPharase();
        this.datePost = obj.getDatePost();
        this.likes = obj.getLikes().size();
        this.respond = obj.getRespods().size();
        this.postId = obj.getPostId();
    }

    public CommentDTO(){
        
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPharase() {
        return pharase;
    }

    public void setPharase(String pharase) {
        this.pharase = pharase;
    }

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public int getRespond() {
        return respond;
    }

    public void setRespond(int respond) {
        this.respond = respond;
    }
}
