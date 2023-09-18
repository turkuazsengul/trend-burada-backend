package com.example.msapiuser.Converter;

import com.example.msapiuser.Core.AbstractBaseConverter;
import com.example.msapiuser.Entity.UserEntity;
import com.example.msapiuser.Model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractBaseConverter<UserDto, UserEntity> {
}
