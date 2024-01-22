package com.example.msapiuser.Service;

import com.example.msapiuser.Model.UserDto;

import java.util.List;

public interface ManagementService {
    UserDto getUser(int userId);
    List<UserDto> getAllUser();
}
