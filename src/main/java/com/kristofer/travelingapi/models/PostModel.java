package com.kristofer.travelingapi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.kristofer.travelingapi.dtos.AuthorDTO;

@Document(collection="post")
public class PostModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String phrase;
    private String img;
    private List<UserModel> likes = new ArrayList<>();
    private List<UserModel> favs = new ArrayList<>();
    private Date date;
    //@DBRef(lazy = true)
    private String authorId;
    private AuthorDTO author;

    public PostModel(String id, String phrase, String img,  Date date, 
    String author) 
    {
        super();
        this.id = id;
        this.phrase = phrase;
        this.img = img;
        this.date = date;
        this.authorId = author;
    }

    public PostModel(){

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

    public List<UserModel> getLikes() {
        return likes;
    }

    public void setLikes(UserModel likes) {
        this.likes.add(likes);
    }

    public List<UserModel> getFavs() {
        return favs;
    }

    public void setFavs(UserModel favs) {
        this.favs.add(favs);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String author) {
        this.authorId = author;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PostModel other = (PostModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
