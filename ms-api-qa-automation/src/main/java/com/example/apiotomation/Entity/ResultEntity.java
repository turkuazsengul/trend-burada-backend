package com.example.apiotomation.Entity;

import com.example.apiotomation.Core.BaseDto;
import com.example.apiotomation.Core.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "test_results")
public class ResultEntity extends BaseEntity {

    @Column(name = "run_time")
    private String runTime;

    @Column(name = "service_url")
    private String serviceUrl;

    @Column(name = "request_method_type")
    private String requestMethodType;

    @Column(name = "result_status_code")
    private int resultStatusCode;

    @Column(name = "result_status")
    private String resultStatus;

    @Column(name = "provided_json_response_token")
    private String providedJsonResponseToken;

    @Column(name = "expected_json_response_token")
    private String expectedJsonResponseToken;

    @Column(name = "test_result_status_message")
    private String testResultStatusMessage;
}
