package com.kristofer.travelingapi.dtos;

import java.io.Serializable;
import java.util.Date;

import com.kristofer.travelingapi.models.PostModel;

public class PostDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String phrase;
    private String img;
    private int likes;
    private int comments;
    private Date date;
    private AuthorDTO author;
    public PostDTO(PostModel obj) {
        this.id = obj.getId();
        this.phrase = obj.getPhrase();
        this.img = obj.getImg();
        this.likes = obj.getLikes().size();
        this.comments = obj.getComments().size();
        this.date = obj.getDate();
    }
    public PostDTO(){
        
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
    public String getPhrase() {
        return phrase;
    }
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }
    public int getComments() {
        return comments;
    }
    public void setComments(int comments) {
        this.comments = comments;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public AuthorDTO getAuthor() {
        return author;
    }
    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

}
