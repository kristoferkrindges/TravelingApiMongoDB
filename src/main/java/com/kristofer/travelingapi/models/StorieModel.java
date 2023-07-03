package com.kristofer.travelingapi.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.kristofer.travelingapi.dtos.AuthorDTO;

@Document(collection = "storie")
public class StorieModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    private String video;
    private Date datepublic;
    private String authorId;
    private AuthorDTO author;

    public StorieModel(String video, Date datepublic, String authorId) {
        this.video = video;
        this.datepublic = datepublic;
        this.authorId = authorId;
    }
    public StorieModel(StorieModel obj){
        this.video = obj.getVideo();
        this.authorId = obj.getAuthorId();
        this.datepublic = obj.getDatepublic();
    }
    public StorieModel(){

    }
    public String getVideo() {
        return video;
    }
    public void setVideo(String video) {
        this.video = video;
    }
    public Date getDatepublic() {
        return datepublic;
    }
    public void setDatepublic(Date datepublic) {
        this.datepublic = datepublic;
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

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((video == null) ? 0 : video.hashCode());
        result = prime * result + ((datepublic == null) ? 0 : datepublic.hashCode());
        result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
        result = prime * result + ((author == null) ? 0 : author.hashCode());
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
        StorieModel other = (StorieModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (video == null) {
            if (other.video != null)
                return false;
        } else if (!video.equals(other.video))
            return false;
        if (datepublic == null) {
            if (other.datepublic != null)
                return false;
        } else if (!datepublic.equals(other.datepublic))
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
        return true;
    }
    
}
