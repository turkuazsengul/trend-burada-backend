package com.example.msapiuser.Model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KeycloakUserDto {
    private String createdTimestamp;
    @NotNull
    private boolean enabled;
    private String totp;
    @NotNull
    private Boolean emailVerified;
    @NotNull
    private String firstName;
    @NotNull
    private String username;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    private List<KeycloakCredentials> credentials;
    private List<String> realmRoles;
    private KeycloakAccess access;
}
