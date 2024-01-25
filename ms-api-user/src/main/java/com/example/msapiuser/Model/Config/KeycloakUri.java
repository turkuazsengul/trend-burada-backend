package com.example.msapiuser.Model.Config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "uri")
public class KeycloakUri {
    private String users;
    private String userById;
    private String userWithRole;
    private String roles;
    private String clients;
}
