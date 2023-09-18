package com.example.msapiuser.Core.Response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Response<T> extends BaseResponse {
    private int resultCount;
    private List<T> returnData;

    public Response(){
        this.returnData = new ArrayList<>();
    }

    public void addList(T obj){
        returnData.add(obj);
    }

}
