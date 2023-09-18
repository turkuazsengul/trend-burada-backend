package com.example.msapiuser.Service;

import com.example.msapiuser.Core.Response.BaseResponse;
import com.example.msapiuser.Core.Response.Response;
import com.example.msapiuser.Exception.RegisterExceptions;
import com.example.msapiuser.Model.UserDto;

public interface AuthService {
    UserDto login(String username);

    BaseResponse register(UserDto userDto);

    BaseResponse confirm(int userId, int confirmCode);

    Response<UserDto> forgotPassword(int userId, int confirmCode);

    BaseResponse createConfirm(int userId);
}
