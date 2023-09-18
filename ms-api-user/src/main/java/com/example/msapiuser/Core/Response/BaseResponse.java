package com.example.msapiuser.Core.Response;

import lombok.Data;

@Data
public class BaseResponse {
    private int returnCode;
    private String returnMessage;
}
