package com.example.msapiuser.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
    private String id;
    private boolean emailVerified;
    private String name;
    private String username;
    private String FirstName;
    private String LastName;
    private String email;
}
