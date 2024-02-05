package com.example.msapiuser.Controller;

import com.example.msapiuser.Core.Response.BaseResponse;
import com.example.msapiuser.Core.ResponseMapper;
import com.example.msapiuser.Model.KeycloakUserInfo;
import com.example.msapiuser.Model.UserDto;
import com.example.msapiuser.Model.UserInfo;
import com.example.msapiuser.Service.Impl.AuthServiceImpl;
import com.example.msapiuser.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/management/")
@CrossOrigin
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final ResponseMapper responseMapper;

    @GetMapping("/get")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<BaseResponse> getUser(@RequestParam String userId) {
        BaseResponse baseResponse;
        try {
            UserDto user = userService.getUser(userId);

            baseResponse = responseMapper.response2Map(user);
        } catch (WebClientException e) {
            logger.error("Confirm Proses Fail: " + e.getMessage());
            baseResponse = responseMapper.exceptionalResponse2Map(e);
        } catch (Exception ex) {
            logger.error("Unknown Error: " + ex.getMessage());
            baseResponse = responseMapper.exceptionalResponse2Map(ex);
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> addUser(@RequestBody UserDto userDto) {
        BaseResponse baseResponse;
        try {
            UserDto user = userService.addUser(userDto);

            baseResponse = responseMapper.response2Map(user);
        } catch (WebClientException e) {
            logger.error("Confirm Proses Fail: " + e.getMessage());
            baseResponse = responseMapper.exceptionalResponse2Map(e);
        } catch (Exception ex) {
            logger.error("Unknown Error: " + ex.getMessage());
            baseResponse = responseMapper.exceptionalResponse2Map(ex);
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/update")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<BaseResponse> updateUser(@RequestBody UserDto userDto) {
        BaseResponse baseResponse;
        try {
            UserDto user = userService.updateUser(userDto);

            baseResponse = responseMapper.response2Map(user);
        } catch (WebClientException e) {
            logger.error("Confirm Proses Fail: " + e.getMessage());
            baseResponse = responseMapper.exceptionalResponse2Map(e);
        } catch (Exception ex) {
            logger.error("Unknown Error: " + ex.getMessage());
            baseResponse = responseMapper.exceptionalResponse2Map(ex);
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN-CLI')")
    public ResponseEntity<BaseResponse> deleteUser(@RequestParam String userId) {
        BaseResponse baseResponse;
        try {
            userService.deleteUser(userId);
            baseResponse = responseMapper.response2Map(null);
        } catch (WebClientException e) {
            logger.error("Confirm Proses Fail: " + e.getMessage());
            baseResponse = responseMapper.exceptionalResponse2Map(e);
        } catch (Exception ex) {
            logger.error("Unknown Error: " + ex.getMessage());
            baseResponse = responseMapper.exceptionalResponse2Map(ex);
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
