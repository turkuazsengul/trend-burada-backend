package com.example.msapiuser.Service;

import com.example.msapiuser.Model.UserDto;

public interface UserService {
    UserDto addUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    UserDto getUser(String userId);
    void deleteUser(String userId);

}
