package com.kristofer.travelingapi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class CommentModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    private String pharase;
    private Date datePost;
    private List<String> likes = new ArrayList<>();
    @DBRef(lazy = true)
    private List<RespondModel> respods = new ArrayList<>();
    private String authorId;
    private String postId;

    public CommentModel(String id, String pharase, Date datePost,
            String authorId, String postId) {
        this.id = id;
        this.pharase = pharase;
        this.datePost = datePost;
        this.authorId = authorId;
        this.postId = postId;
    }

    public CommentModel(CommentModel obj) {
        this.id = obj.getId();
        this.pharase = obj.getPharase();
        this.datePost = obj.getDatePost();
        this.likes = obj.getLikes();
        this.respods = obj.getRespods();
        this.authorId = obj.getAuthorId();
        this.postId = obj.getPostId();
    }

    public CommentModel(){

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

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public List<RespondModel> getRespods() {
        return respods;
    }

    public void setRespods(List<RespondModel> respods) {
        this.respods = respods;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((pharase == null) ? 0 : pharase.hashCode());
        result = prime * result + ((datePost == null) ? 0 : datePost.hashCode());
        result = prime * result + ((likes == null) ? 0 : likes.hashCode());
        result = prime * result + ((respods == null) ? 0 : respods.hashCode());
        result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
        result = prime * result + ((postId == null) ? 0 : postId.hashCode());
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
        CommentModel other = (CommentModel) obj;
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
        if (respods == null) {
            if (other.respods != null)
                return false;
        } else if (!respods.equals(other.respods))
            return false;
        if (authorId == null) {
            if (other.authorId != null)
                return false;
        } else if (!authorId.equals(other.authorId))
            return false;
        if (postId == null) {
            if (other.postId != null)
                return false;
        } else if (!postId.equals(other.postId))
            return false;
        return true;
    }

}
