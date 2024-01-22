package com.example.msapiuser.Core;

import com.example.msapiuser.Core.Response.BaseResponseCodeEnum;
import com.example.msapiuser.Core.Response.BaseResponseMessageEnum;
import com.example.msapiuser.Core.Response.ExceptionalResponse;
import com.example.msapiuser.Core.Response.Response;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ResponseMapper {

    public Response<Object> response2Map(Object data){
        Response<Object> baseResponse = new Response<>();

        if (data instanceof List<?>){
            List<Object> dataList = (List<Object>) data;

            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.setResultCount(dataList.size());
            baseResponse.setReturnData(dataList);
        }else {
            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.setResultCount(1);
            baseResponse.addList(data);
        }

        return baseResponse;
    }

    public ExceptionalResponse exceptionalResponse2Map(Exception e){
        ExceptionalResponse baseResponse = new ExceptionalResponse(e);

        baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
        baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());

        return baseResponse;
    }

    public ExceptionalResponse exceptionalResponse2Map(String message, String detailMessage){
        ExceptionalResponse baseResponse = new ExceptionalResponse(message,detailMessage);

        baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
        baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());

        return baseResponse;
    }
}
