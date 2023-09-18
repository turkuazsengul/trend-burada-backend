package com.example.msapiuser.Model;

import com.example.msapiuser.Core.BaseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto extends BaseDto {

    private String email;
    private String password;
    private String name;
    private String surname;
    private String cinsiyet;
    private String adres;
    private String gsm_no;
    @JsonIgnore
    private boolean isEnable;
    @JsonIgnore
    private int confirmCode;
    private Date confirmCodeCreatedTime;
    private List<RoleDto> roleList;
}
