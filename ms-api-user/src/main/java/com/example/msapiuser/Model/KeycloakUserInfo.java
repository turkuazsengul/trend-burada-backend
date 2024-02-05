package com.example.msapiuser.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeycloakUserInfo {
    private String sub;
    private boolean email_verified;
    private String name;
    private String preferred_username;
    private String given_name;
    private String family_name;
    private String email;
}
