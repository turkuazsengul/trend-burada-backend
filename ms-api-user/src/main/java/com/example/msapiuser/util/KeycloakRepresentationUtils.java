package com.example.msapiuser.util;

import com.example.msapiuser.Model.KeycloakCredentials;
import com.example.msapiuser.Model.KeycloakUserDto;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class KeycloakRepresentationUtils {
    public static CredentialRepresentation getCredentialRepresentation(KeycloakUserDto keycloakUserDto) {
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        Optional<KeycloakCredentials> keycloakCredentials = keycloakUserDto.getCredentials().stream().findFirst();
        if(keycloakCredentials.isPresent()){
            passwordCred.setTemporary(keycloakCredentials.get().isTemporary());
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(keycloakCredentials.get().getValue());
        }
        return passwordCred;
    }

    public static UserRepresentation getUserRepresentation(KeycloakUserDto keycloakUserDto, String clientId) {
        Map<String, List<String>> clientRoles = new HashMap<>();
        clientRoles.put(clientId, keycloakUserDto.getRealmRoles());

        UserRepresentation user = new UserRepresentation();
        user.setEnabled(keycloakUserDto.isEnabled());
        user.setUsername(keycloakUserDto.getUsername());
        user.setFirstName(keycloakUserDto.getFirstName());
        user.setLastName(keycloakUserDto.getLastName());
        user.setEmail(keycloakUserDto.getEmail());
        user.setEmailVerified(keycloakUserDto.getEmailVerified());
        user.setClientRoles(clientRoles);
        return user;
    }
}
