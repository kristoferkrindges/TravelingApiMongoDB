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

}
