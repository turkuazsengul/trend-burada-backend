package com.example.msapiuser.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeycloakAccess {
    private boolean manageGroupMembership;
    private boolean view;
    private boolean mapRoles;
    private boolean impersonate;
    private boolean manage;
}
