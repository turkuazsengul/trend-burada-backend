package com.example.msapiuser.Service.Impl;

import com.example.msapiuser.Converter.UserConverter;

import com.example.msapiuser.Entity.UserEntity;
import com.example.msapiuser.Model.UserDto;
import com.example.msapiuser.Repository.UserRepository;
import com.example.msapiuser.Service.KeycloakAdminClientService;
import com.example.msapiuser.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final KeycloakAdminClientService keycloakAdminClientService;

    @Override
    public UserDto addUser(UserDto userDto) {
        UserEntity userEntity = userRepository.save(userConverter.convertToEntity(userDto));
        return userConverter.convertToDto(userEntity);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserEntity userEntity = userRepository.save(userConverter.convertToEntity(userDto));
        return userConverter.convertToDto(userEntity);
    }

    @Override
    public UserDto getUser(String userId) {
        return userConverter.convertToDto(userRepository.findByPkId(userId));
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {
        keycloakAdminClientService.deleteUser(userId);
        userRepository.deleteByPkId(userId);
    }


}
