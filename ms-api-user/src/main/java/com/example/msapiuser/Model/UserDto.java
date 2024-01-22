package com.example.msapiuser.Model;

import com.example.msapiuser.Core.BaseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto extends BaseDto {
    private String email;
    private String name;
    private String surname;
    private String gender;
    private String address;
    private String gsm_no;
    @JsonIgnore
    private boolean isEnable;
}
