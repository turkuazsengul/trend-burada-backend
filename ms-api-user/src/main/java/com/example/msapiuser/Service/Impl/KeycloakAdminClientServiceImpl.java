package com.example.msapiuser.Service.Impl;

import com.example.msapiuser.Exception.CustomUserException;
import com.example.msapiuser.Model.Config.KeycloakConfig;
import com.example.msapiuser.Model.KeycloakUserDto;
import com.example.msapiuser.Model.KeycloakUserInfo;
import com.example.msapiuser.Service.KeycloakAdminClientService;
import com.google.common.net.HttpHeaders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.ws.rs.core.Response;
import java.util.*;

import static com.example.msapiuser.util.KeycloakRepresentationUtils.*;

@Service
@RequiredArgsConstructor
public class KeycloakAdminClientServiceImpl implements KeycloakAdminClientService {
    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final KeycloakConfig keycloakConfig;
    private final Keycloak keycloak;

    @Value("${keycloak-admin.server-url}")
    private String serverUrl;

    @Override
    public void createUser(KeycloakUserDto keycloakUserDto) {
        RealmResource realmResource = keycloak.realm(keycloakConfig.getRealmName());

        UserResource userResource = addUser(keycloakUserDto, realmResource);
        addPassword2User(keycloakUserDto, userResource);
        addRole2User(keycloakUserDto, realmResource, userResource);

    }

    @Override
        public String getUserId(String username) {
        RealmResource realmResource = keycloak.realm(keycloakConfig.getRealmName());
        UsersResource usersResource = realmResource.users();

        UserRepresentation userRepresentation = usersResource.search(username).get(0);

        return userRepresentation.getId();
    }

    @Override
    public KeycloakUserInfo getUserInfo(HttpServletRequest request) {
        String userToken = getTokenByServletRequest(request);

        WebClient webClient = WebClient.builder()
                .baseUrl(keycloakConfig.getUri().getUserInfo())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
                .build();

        ResponseEntity<KeycloakUserInfo> response = webClient.get()
                .retrieve()
                .toEntity(KeycloakUserInfo.class)
                .block();

        assert response != null;
        return response.getBody();
    }

    @Override
    public AccessTokenResponse getUserAccessToken(String username, String password) {
        Keycloak keycloakUser = KeycloakBuilder
                .builder()
                .serverUrl(serverUrl)
                .grantType(OAuth2Constants.PASSWORD)
                .realm(keycloakConfig.getRealmName())
                .clientId(keycloakConfig.getClientName())
                .username(username)
                .password(password)
                .build();

        return keycloakUser.tokenManager().getAccessToken();
    }

    @Override
    public void deleteUser(String userId) {
        try{
            RealmResource realmResource = keycloak.realm(keycloakConfig.getRealmName());
            UsersResource usersResource = realmResource.users();
            Response delete = usersResource.delete(userId);
            delete.close();
        }catch (Exception e){
            logger.error("User delete operation error on Keycloak Server");
        }
    }

    @Override
    public void logout(HttpServletRequest request) {
        String userToken = getTokenByServletRequest(request);

        WebClient webClient = WebClient.builder()
                .baseUrl(keycloakConfig.getUri().getUserInfo())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + userToken)
                .build();

        ResponseEntity<String> response = webClient.post()
                .retrieve()
                .toEntity(String.class)
                .block();
    }

    private String getTokenByServletRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (ObjectUtils.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);

        } else {
            throw new CustomUserException("HttpServletRequest Authorization Error.");
        }
    }

    private UserResource addUser(KeycloakUserDto keycloakUserDto, RealmResource realmResource) {
        UserRepresentation user = getUserRepresentation(keycloakUserDto, keycloakConfig.getClientName());
        UsersResource usersResource = realmResource.users();

        Response response = usersResource.create(user);

        if (response.getStatus() != 201) {
            logger.error("Fail for Create User: " + response.readEntity(String.class));
            throw new CustomUserException("Fail for Create User: " + response.readEntity(String.class));
        }
        String userId = CreatedResponseUtil.getCreatedId(response);

        return usersResource.get(userId);
    }

    private void addPassword2User(KeycloakUserDto keycloakUserDto, UserResource userResource) {
        CredentialRepresentation passwordCred = getCredentialRepresentation(keycloakUserDto);
        userResource.resetPassword(passwordCred);
    }

    /**
     * Mevcut sistemde şuan tek bir role ekleme tasarlandı. Birden fazla rol ekleme durumu söz konusu olduğunda bu methodun listenin
     * tamamnını handle edecek şekilde güncellenmesi gerekir.
     */
    private void addRole2User(KeycloakUserDto keycloakUserDto, RealmResource realmResource, UserResource userResource) {
        String roleName = keycloakUserDto.getRealmRoles().get(0);
        String clientId = keycloakConfig.getClientName();

        ClientRepresentation client = realmResource.clients().findByClientId(clientId).get(0);
        RoleRepresentation userClientRole = realmResource.clients().get(client.getId()).roles().get(roleName).toRepresentation();
        userResource.roles().clientLevel(client.getId()).add(Collections.singletonList(userClientRole));
    }

}
