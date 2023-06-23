package com.kristofer.travelingapi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String imgUrl;
    private String at;
    //private List<Posts> fav = new ArrayList<>();
    // private List<Posts> likes = new ArrayList<>();
    private List<UserModel> followers;
    private List<UserModel> following;
    // private List<Posts> posts = new ArrayList<>();
    // private List<Posts> configs = new ArrayList<>();

    public UserModel(String id, String name, String email, String password, String imgUrl, String at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.imgUrl = imgUrl;
        this.at = at;
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public UserModel(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public List<UserModel> getFollowing() {
        return following;
    }

    public void setFollowing(UserModel following) {
        this.following.add(following);
    }

    public List<UserModel> getFollowers() {
        return followers;
    }

    public void setFollowers(UserModel followers) {
        this.followers.add(followers);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserModel other = (UserModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
