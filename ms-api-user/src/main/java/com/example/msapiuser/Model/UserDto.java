package com.example.msapiuser.Model;

import com.example.msapiuser.Core.BaseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.keycloak.representations.AccessTokenResponse;

@Getter
@Setter
public class UserDto extends BaseDto {
    private String email;
    private String name;
    private String surname;
    private String gender;
    private String address;
    private String gsm_no;
    private String dob;
    @JsonIgnore
    private boolean isEnable;
}
