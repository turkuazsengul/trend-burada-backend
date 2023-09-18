package com.example.msapiuser.Model;

import com.example.msapiuser.Core.BaseDto;
import lombok.Data;

import javax.persistence.Column;

@Data
public class RoleDto extends BaseDto {
    private String name;
}
