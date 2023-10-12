package com.example.apiotomation.Dto;

import com.example.apiotomation.Core.BaseDto;
import lombok.Data;

import javax.persistence.Column;

@Data
public class ResultDto extends BaseDto {
    private String runTime;
    private String serviceUrl;
    private String requestMethodType;
    private int resultStatusCode;
    private String resultStatus;
    private String providedJsonResponseToken;
    private String expectedJsonResponseToken;
    private String testResultStatusMessage;
}
