package com.example.msapiuser.Service;

import com.example.msapiuser.Model.KeycloakUserDto;
import com.example.msapiuser.Model.UserDto;

public interface KeycloakService {
    void addUser(KeycloakUserDto keycloakUserDto);
    void getAllUser();
    void getUserById(String id);
}
