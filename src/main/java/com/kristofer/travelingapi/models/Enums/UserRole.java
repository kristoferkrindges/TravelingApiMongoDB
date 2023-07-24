package com.kristofer.travelingapi.models.Enums;

public enum UserRole {
    ADMIN("admin"),
    CREATE("create"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
