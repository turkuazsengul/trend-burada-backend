package com.example.msapiuser.Service.Impl;

import com.example.msapiuser.Model.Config.Keycloak;
import com.example.msapiuser.Model.KeycloakAuthResponse;
import com.example.msapiuser.Service.KeycloakAuthService;
import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
@RequiredArgsConstructor
public class KeycloakAuthServiceImpl implements KeycloakAuthService {
    private final Keycloak keycloakConfig;

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "Pa55w0rd";

    @Override
    public String authenticationToken() {
        MultiValueMap<String, String> formDataBody1 = getFormDataBody();

        WebClient webClient = WebClient.builder()
                .baseUrl(keycloakConfig.getMasterAuthUri())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();

        ResponseEntity<KeycloakAuthResponse> response = webClient.post()
                .body(BodyInserters.fromFormData(formDataBody1))
                .retrieve()
                .toEntity(KeycloakAuthResponse.class)
                .block();

        assert response != null;
        return Objects.requireNonNull(response.getBody()).getAccess_token();
    }

    private MultiValueMap<String, String> getFormDataBody() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("client_id", List.of("admin-cli"));
        map.put("username", List.of(USERNAME));
        map.put("password", List.of(PASSWORD));
        map.put("grant_type", List.of("password"));
        map.put("client_secret", List.of(keycloakConfig.getClientSecret()));
        return map;
    }
}
