package com.common.mscommonsecurity.configuration;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfiguration {

    @Bean
    Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8180/auth")
                .realm("master")
                .clientId("admin-cli")
                .clientSecret("fNqy5SkbKladT9KBogw75oO2LwEfIIT5")
                .grantType(OAuth2Constants.PASSWORD)
                .username("admin")
                .password("Pa55w0rd")
                .build();
    }
}
