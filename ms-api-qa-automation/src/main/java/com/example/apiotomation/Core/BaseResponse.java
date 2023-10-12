package com.example.apiotomation.Core;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseResponse<T> {
    private int returnCode;
    private String returnMessage;
    private List<T> testResults;

    public BaseResponse(){
        this.testResults = new ArrayList<>();
    }

    public void addListToTestResults(T obj){
        testResults.add(obj);
    }

}
