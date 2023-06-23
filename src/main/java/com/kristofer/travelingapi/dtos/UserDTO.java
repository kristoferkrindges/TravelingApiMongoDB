package com.kristofer.travelingapi.dtos;

import java.io.Serializable;
import java.util.List;

import com.kristofer.travelingapi.models.UserModel;

public class UserDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String name;
    private String imgUrl;
    private String at;
    private List<UserModel> followers;
    private List<UserModel> following;


    public UserDTO(UserModel obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.imgUrl = obj.getImgUrl();
        this.at = obj.getAt();
        this.followers = obj.getFollowers();
        this.following = obj.getFollowing();
    }

    public UserDTO(){
        
    }

    public List<UserModel> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserModel> followers) {
        this.followers = followers;
    }

    public List<UserModel> getFollowing() {
        return following;
    }

    public void setFollowing(List<UserModel> following) {
        this.following = following;
    }

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
}
