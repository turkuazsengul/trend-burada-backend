package com.example.apiotomation.Modal;

import lombok.Data;

@Data
public class RequestModal {
    private String apiUrl;
    private String expectedResultJsonToken;
    private String expectedResultCount;
    private String apiMethodType;
}
