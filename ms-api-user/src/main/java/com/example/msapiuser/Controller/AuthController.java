package com.example.msapiuser.Controller;

import com.example.msapiuser.Core.*;
import com.example.msapiuser.Core.Response.BaseResponse;
import com.example.msapiuser.Core.Response.BaseResponseCodeEnum;
import com.example.msapiuser.Core.Response.BaseResponseMessageEnum;
import com.example.msapiuser.Core.Response.Response;
import com.example.msapiuser.Exception.RegisterExceptions;
import com.example.msapiuser.Model.UserDto;
import com.example.msapiuser.Service.AuthService;
import com.example.msapiuser.Service.Impl.AuthServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private ResponseMapper responseMapper;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> register(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(authService.register(userDto), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = authService.login((String) auth.getPrincipal());

        logger.info("Success Login ==>  User: " + userDto);
        return new ResponseEntity<>(responseMapper.response2Map(userDto), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/confirm")
    public ResponseEntity<BaseResponse> confirm(@RequestParam int userId, @RequestParam int confirmCode) {
        return new ResponseEntity<>(authService.confirm(userId, confirmCode), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/createConfirm")
    public ResponseEntity<BaseResponse> createConfirm(@RequestParam int userId) {
        return new ResponseEntity<>(authService.createConfirm(userId), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/forgotPassword")
    public ResponseEntity<Response<UserDto>> forgotPassword(@RequestParam int userId, @RequestParam int confirmCode) {
        return new ResponseEntity<>(authService.forgotPassword(userId, confirmCode), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAuthority(T(com.example.msapiuser.Constants.RoleConstants).ROLE_CUSTOMER)")
    @GetMapping(value = "/logout")
    public ResponseEntity<Boolean> logOut() {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
