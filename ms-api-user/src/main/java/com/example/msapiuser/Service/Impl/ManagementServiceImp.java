package com.example.msapiuser.Service.Impl;

import com.example.msapiuser.Converter.UserConverter;
import com.example.msapiuser.Entity.UserEntity;
import com.example.msapiuser.Model.UserDto;
import com.example.msapiuser.Repository.UserRepository;
import com.example.msapiuser.Service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagementServiceImp implements ManagementService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public UserDto getUser(int userId) {
        UserEntity userEntity = userRepository.findByPkId(userId);
        return userConverter.convertToDto(userEntity);
    }

    @Override
    public List<UserDto> getAllUser() {
        return userConverter.convertToDtoList(userRepository.findAll());
    }
}
