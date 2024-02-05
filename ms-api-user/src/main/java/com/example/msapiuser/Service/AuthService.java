package com.example.msapiuser.Service;

import com.example.msapiuser.Core.Response.BaseResponse;
import com.example.msapiuser.Model.KeycloakUserDto;
import com.example.msapiuser.Model.UserDto;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    BaseResponse register(KeycloakUserDto keycloakUserDto);
    BaseResponse login(String authorizationHeader);
    BaseResponse logout(String userId);
}
