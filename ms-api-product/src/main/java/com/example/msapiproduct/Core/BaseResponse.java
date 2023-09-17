package com.example.msapiproduct.Core;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseResponse<T> {
    private int returnCode;
    private String returnMessage;
    private int resultCount;
    private List<T> returnData;

    public BaseResponse(){
        this.returnData = new ArrayList<>();
    }

    public void addList(T obj){
        returnData.add(obj);
    }
}
