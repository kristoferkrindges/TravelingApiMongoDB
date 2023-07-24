package com.kristofer.travelingapi.dtos;

import java.util.Date;

import com.kristofer.travelingapi.models.Enums.UserRole;

public record RegisterUserDTO(String name, String email, String at, String password, 
Date birthdate, UserRole role) {
    
}
