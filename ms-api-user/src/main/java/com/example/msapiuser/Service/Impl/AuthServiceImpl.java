package com.example.msapiuser.Service.Impl;

import com.example.msapiuser.Converter.UserConverter;
import com.example.msapiuser.Core.Response.BaseResponse;
import com.example.msapiuser.Core.ResponseMapper;
import com.example.msapiuser.Entity.UserEntity;
import com.example.msapiuser.Exception.CustomUserException;
import com.example.msapiuser.Model.KeycloakUserDto;
import com.example.msapiuser.Model.LoginResponseDto;
import com.example.msapiuser.Model.UserDto;
import com.example.msapiuser.Repository.UserRepository;
import com.example.msapiuser.Service.AuthService;
import com.example.msapiuser.Service.KeycloakAdminClientService;
import com.example.msapiuser.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClientException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final ResponseMapper responseMapper;
    private final KeycloakAdminClientService keycloakAdminClientService;
    private final UserService userService;
    private final UserConverter userConverter;

    @Override
    @Transactional
    public BaseResponse register(KeycloakUserDto keycloakUserDto) {
        BaseResponse baseResponse;
        try {
            keycloakAdminClientService.createUser(keycloakUserDto);
            String userId = keycloakAdminClientService.getUserId(keycloakUserDto.getUsername());

            UserEntity userEntity = getUserEntity(userId, keycloakUserDto);
            UserDto userDto = userService.addUser(userConverter.convertToDto(userEntity));

            baseResponse = responseMapper.response2Map(userDto);
        } catch (WebClientException e) {
            logger.error("Confirm Proses Fail: " + e.getMessage());
            baseResponse = responseMapper.exceptionalResponse2Map(e);
        } catch (Exception ex) {
            logger.error("Unknown Error: " + ex.getMessage());
            baseResponse = responseMapper.exceptionalResponse2Map(ex);
        }

        return baseResponse;
    }

    @Override
    public BaseResponse login(String authorizationHeader) {
        BaseResponse baseResponse;
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
                String base64Credentials = authorizationHeader.substring(6).trim();
                String credentials = new String(java.util.Base64.getDecoder().decode(base64Credentials));
                String[] parts = credentials.split(":", 2);
                String username = parts[0];
                String password = parts[1];

                AccessTokenResponse accessTokenResponse = keycloakAdminClientService.getUserAccessToken(username, password);

                String userId = keycloakAdminClientService.getUserId(username);
                UserDto userDto = userService.getUser(userId);

                loginResponseDto.setUser(userDto);
                loginResponseDto.setAccessTokenResponse(accessTokenResponse);

                baseResponse = responseMapper.response2Map(loginResponseDto);
            } else {
                throw new CustomUserException("User authentication information is empty on this request.");
            }
        } catch (CustomUserException customUserException) {
            logger.error("Authorization information could not found");
            baseResponse = responseMapper.exceptionalResponse2Map(customUserException);
        } catch (Exception e) {
            baseResponse = responseMapper.exceptionalResponse2Map(e);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse logout(String userId) {
        return null;
    }

    private UserEntity getUserEntity(String userId, KeycloakUserDto keycloakUserDto) {
        UserEntity user = new UserEntity();

        user.setPkId(userId);
        user.setEmail(keycloakUserDto.getEmail());
        user.setEnable(keycloakUserDto.isEnabled());
        user.setAddress(keycloakUserDto.getAddress());
        user.setName(keycloakUserDto.getFirstName());
        user.setSurname(keycloakUserDto.getLastName());
        user.setGsm_no(keycloakUserDto.getPhoneNumber());
        user.setDob(keycloakUserDto.getDateOfBirth());

        return user;
    }
}
