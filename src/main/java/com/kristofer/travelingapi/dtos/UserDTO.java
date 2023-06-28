package com.kristofer.travelingapi.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.kristofer.travelingapi.models.PostModel;
import com.kristofer.travelingapi.models.UserModel;

public class UserDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String name;
    private String photo;
    private Date birthdate;
    private String at;
    private int followers;
    private int following;

    public UserDTO(UserModel obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.photo = obj.getPhoto();
        this.at = obj.getAt();
        this.birthdate = obj.getBirthdate();
        this.followers = obj.lenghtFollowers();
        this.following = obj.lenghtFollowing();
    }

    public UserDTO(){
        
    }
    
    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
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
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

}
