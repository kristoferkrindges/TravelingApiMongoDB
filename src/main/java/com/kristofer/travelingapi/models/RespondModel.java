package com.kristofer.travelingapi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.kristofer.travelingapi.dtos.AuthorDTO;

@Document(collection = "respond")
public class RespondModel implements Serializable{
    @Id
    private String id;
    private String pharase;
    private Date datePost;
    private List<String> likes = new ArrayList<>();
    private String authorId;
    private AuthorDTO author;
    private String commentId;
    public RespondModel(String id, String pharase, Date datePost, String authorId,
            String commentId) {
        this.id = id;
        this.pharase = pharase;
        this.datePost = datePost;
        this.authorId = authorId;
        this.commentId = commentId;
    }

    public RespondModel(RespondModel obj){
        this.id = obj.getId();
        this.pharase = obj.getPharase();
        this.datePost = obj.getDatePost();
        this.likes = obj.getLikes();
        this.authorId = obj.getAuthorId();
        this.commentId = obj.getCommentId();
    }

    public RespondModel(){

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
    public List<String> getLikes() {
        return likes;
    }
    public void setLikes(List<String> likes) {
        this.likes = likes;
    }
    public String getAuthorId() {
        return authorId;
    }
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
    public AuthorDTO getAuthor() {
        return author;
    }
    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }
    public String getCommentId() {
        return commentId;
    }
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((pharase == null) ? 0 : pharase.hashCode());
        result = prime * result + ((datePost == null) ? 0 : datePost.hashCode());
        result = prime * result + ((likes == null) ? 0 : likes.hashCode());
        result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((commentId == null) ? 0 : commentId.hashCode());
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
        RespondModel other = (RespondModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (pharase == null) {
            if (other.pharase != null)
                return false;
        } else if (!pharase.equals(other.pharase))
            return false;
        if (datePost == null) {
            if (other.datePost != null)
                return false;
        } else if (!datePost.equals(other.datePost))
            return false;
        if (likes == null) {
            if (other.likes != null)
                return false;
        } else if (!likes.equals(other.likes))
            return false;
        if (authorId == null) {
            if (other.authorId != null)
                return false;
        } else if (!authorId.equals(other.authorId))
            return false;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (commentId == null) {
            if (other.commentId != null)
                return false;
        } else if (!commentId.equals(other.commentId))
            return false;
        return true;
    }
}
