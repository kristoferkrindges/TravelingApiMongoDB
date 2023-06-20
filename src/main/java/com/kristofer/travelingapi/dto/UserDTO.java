package com.kristofer.travelingapi.dto;

import java.io.Serializable;

import com.kristofer.travelingapi.models.User;

public class UserDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String name;
    private String email;
    private String imgUrl;
    private String at;

    public UserDTO(User obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.email = obj.getEmail();
        this.imgUrl = obj.getImgUrl();
        this.at = obj.getAt();
    }

    public UserDTO(){
        
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
