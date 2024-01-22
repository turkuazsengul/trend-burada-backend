package com.example.msapiuser.Controller;

import com.example.msapiuser.Core.Response.BaseResponse;
import com.example.msapiuser.Core.ResponseMapper;
import com.example.msapiuser.Model.UserDto;
import com.example.msapiuser.Service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user/management/")
public class UserManagement {

    private final ManagementService managementService;
    private final ResponseMapper responseMapper;

    @GetMapping("/getUser")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<BaseResponse> getUser(@RequestParam int userId) {
        BaseResponse baseResponse;
        try {
            UserDto userDto = managementService.getUser(userId);
            baseResponse = responseMapper.response2Map(userDto);
        }catch (Exception e){
            baseResponse = responseMapper.exceptionalResponse2Map(e);
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<BaseResponse> getAllUser() {
        BaseResponse baseResponse;
        try {
            List<UserDto> userDtoList = managementService.getAllUser();
            baseResponse = responseMapper.response2Map(userDtoList);
        }catch (Exception e){
            baseResponse = responseMapper.exceptionalResponse2Map(e);
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
