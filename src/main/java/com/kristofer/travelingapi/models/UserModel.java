package com.kristofer.travelingapi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.kristofer.travelingapi.dtos.AuthorDTO;

@Document(collection="user")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String photo;
    private String banner;
    private String at;
    private Date birthdate;

    private List<String> fav = new ArrayList<>();
    private List<String> likes = new ArrayList<>();
    private List<String> followers = new ArrayList<>();
    private List<String> following = new ArrayList<>();
    @DBRef(lazy = true)
    private List<PostModel> posts = new ArrayList<>();

    public UserModel(String id, String name, String email, String password, 
    String photo, String at, String banner, Date birthdate) 
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.password = password;
        this.photo = photo;
        this.at = at;
        this.banner = banner;
    }

    public UserModel(String id, String name, String email, String password, 
    String photo, String at, String banner, List<String> followers, 
    List<String> following, List<String> fav, List<String> likes , Date birthdate) 
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.password = password;
        this.photo = photo;
        this.at = at;
        this.banner = banner;
        this.followers = followers;
        this.following = following;
        this.fav = fav;
        this.likes = likes;
    }

    public UserModel(){}

    public int lenghtFollowers(){
        return this.followers.size();
    }

    public int lenghtFollowing(){
        return this.following.size();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
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

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following.add(following);
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers.add(followers);
    }

    public List<String> getFav() {
        return fav;
    }

    public void setFav(List<String> fav) {
        this.fav.add(at);
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes.add(likes);
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public List<PostModel> getPosts() {
        return posts;
    }

    public void setPosts(List<PostModel> posts) {
        this.posts = posts;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


}
