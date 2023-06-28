package com.kristofer.travelingapi.dtos;

import java.io.Serializable;

import com.kristofer.travelingapi.models.UserModel;

public class AuthorDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String name;
    private String photo;
    private String banner;
    private String at;
    private int followers;
    private int following;

    public AuthorDTO(){

    }
    public AuthorDTO(UserModel obj){
        this.id = obj.getId();
        this.name = obj.getName();
        this.photo = obj.getPhoto();
        this.banner = obj.getBanner();
        this.at = obj.getAt();
        this.followers = obj.lenghtFollowers();
        this.following = obj.lenghtFollowing();
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
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getBanner() {
        return banner;
    }
    public void setBanner(String banner) {
        this.banner = banner;
    }
    public String getAt() {
        return at;
    }
    public void setAt(String at) {
        this.at = at;
    }
    public int getFollowers() {
        return followers;
    }
    public void setFollowers(int followers) {
        this.followers = followers;
    }
    public int getFollowing() {
        return following;
    }
    public void setFollowing(int following) {
        this.following = following;
    }
}
