package com.example.msapiuser.Controller;

import com.example.msapiuser.Core.Response.BaseResponse;
import com.example.msapiuser.Model.KeycloakUserDto;
import com.example.msapiuser.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public ResponseEntity<BaseResponse> register(@RequestBody KeycloakUserDto keycloakUserDto) {
        return new ResponseEntity<>(authService.register(keycloakUserDto), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestHeader("Authorization") String authorizationHeader) {
        return new ResponseEntity<>(authService.login(authorizationHeader), HttpStatus.OK);
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<BaseResponse> logout(@RequestParam String userId) {
        return new ResponseEntity<>(authService.logout(userId), HttpStatus.OK);
    }
}
