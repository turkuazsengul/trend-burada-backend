package com.example.msapiuser.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeycloakCredentials {
    private String type;
    private String value;
    private boolean temporary;
}
