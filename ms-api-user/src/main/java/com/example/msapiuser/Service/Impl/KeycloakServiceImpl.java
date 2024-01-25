package com.example.msapiuser.Service.Impl;

import com.example.msapiuser.Model.Config.Keycloak;
import com.example.msapiuser.Model.KeycloakUserDto;
import com.example.msapiuser.Model.UserDto;
import com.example.msapiuser.Service.KeycloakAuthService;
import com.example.msapiuser.Service.KeycloakService;
import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {
    private final KeycloakAuthService keycloakAuthService;
    private final Keycloak keycloakConfig;

    @Override
    public void addUser(KeycloakUserDto keycloakUserDto) {
        String bearerToken = keycloakAuthService.authenticationToken();
        String keycloakUrl = keycloakConfig.getUri().getUsers();

        WebClient webClient = WebClient.builder()
                .baseUrl(keycloakUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
                .build();

        ResponseEntity<String> response = webClient.post()
                .body(BodyInserters.fromValue(keycloakUserDto))
                .retrieve()
                .toEntity(String.class)
                .block();
    }

    @Override
    public void getAllUser() {

    }

    @Override
    public void getUserById(String id) {

    }

}
