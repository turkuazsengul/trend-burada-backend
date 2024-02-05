package com.example.msapiuser.Model;

import lombok.Getter;
import lombok.Setter;
import org.keycloak.representations.AccessTokenResponse;

@Getter
@Setter
public class LoginResponseDto {
    private UserDto user;
    private AccessTokenResponse accessTokenResponse;
}
