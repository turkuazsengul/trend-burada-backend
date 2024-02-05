package com.example.msapiuser.Service;

import com.example.msapiuser.Model.KeycloakUserDto;
import com.example.msapiuser.Model.KeycloakUserInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.keycloak.representations.AccessTokenResponse;

import javax.ws.rs.core.Response;

public interface KeycloakAdminClientService {
    void createUser(KeycloakUserDto keycloakUserDto);
    String getUserId(String username);

    KeycloakUserInfo getUserInfo(HttpServletRequest request);

    AccessTokenResponse getUserAccessToken(String username, String password);

    void deleteUser(String userId);

    void logout(HttpServletRequest request);
}
