package com.example.msapiuser.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeycloakAuthResponse {
    private String access_token;
    private int expires_in;
    private int refresh_expires_in;
    private String token_type;
    private String session_state;
    private String scope;
}
