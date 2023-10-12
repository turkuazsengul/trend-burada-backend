package com.example.apiotomation.Modal;

import com.example.apiotomation.Core.BaseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TestResultModal {
    private String testRunTime;
    private String testResultStatus;
    private int testResultStatusCode;
    private String testProvidedResponseJson;
    private String testResultStatusMessage;

    @JsonIgnore
    private String apiUrl;
    @JsonIgnore
    private String methodType;
    @JsonIgnore
    private String testExpectedResponseJson;

}
