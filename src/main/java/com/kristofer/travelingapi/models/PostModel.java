package com.kristofer.travelingapi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="post")
public class PostModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String phrase;
    private String img;
    private List<String> likes;
    private List<String> favs;
    private Date date;
    //Reff
    private String author;

    public PostModel(String id, String phrase, String img,  Date date, 
    String author) 
    {
        super();
        this.id = id;
        this.phrase = phrase;
        this.img = img;
        this.likes = new ArrayList<>();
        this.favs = new ArrayList<>();
        this.date = date;
        this.author = author;
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

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes.add(likes);
    }

    public List<String> getFavs() {
        return favs;
    }

    public void setFavs(String favs) {
        this.favs.add(favs);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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
