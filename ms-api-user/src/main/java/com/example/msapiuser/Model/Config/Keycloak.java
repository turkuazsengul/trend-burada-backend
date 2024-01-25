package com.example.msapiuser.Model.Config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "keycloak")
public class Keycloak {
    private String clientSecret;
    private String realmName;
    private String clientName;
    private String masterAuthUri;
    private KeycloakUri uri;
}
