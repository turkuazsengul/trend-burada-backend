package com.example.msapiuser.configuration;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfiguration {
    @Bean
    Keycloak keycloak(
            @Value("${keycloak-admin.server-url}") String serverUrl,
            @Value("${keycloak-admin.realm-name}") String realmName,
            @Value("${keycloak-admin.client-name}") String clientId,
            @Value("${keycloak-admin.client-secret}") String clientSecret,
            @Value("${keycloak-admin.username}") String username,
            @Value("${keycloak-admin.password}") String password

    ) {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realmName)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.PASSWORD)
                .username(username)
                .password(password)
                .build();
    }
}
