package com.example.msapiuser.Controller;

import com.example.msapiuser.Core.Response.BaseResponse;
import com.example.msapiuser.Model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/management/")
public class UserManagement {
    @GetMapping("/test")
    public String register() {
        return "Authenticated";
    }

}
