package com.example.msapiuser.Core.Response;

import lombok.Data;


@Data
public class ExceptionalResponse extends BaseResponse {
    private ExceptionDetail detail;

    public ExceptionalResponse(Exception e) {
        ExceptionDetail exceptionDetail = new ExceptionDetail();
        exceptionDetail.setExceptionMessage(e.getMessage());
        exceptionDetail.setExceptionDetailMessage(e.getLocalizedMessage());
        this.detail = exceptionDetail;
    }

    public ExceptionalResponse(String message, String detailMessage) {
        ExceptionDetail exceptionDetail = new ExceptionDetail();
        exceptionDetail.setExceptionMessage(message);
        exceptionDetail.setExceptionDetailMessage(detailMessage);
        this.detail = exceptionDetail;
    }
}

@Data
class ExceptionDetail{
    private String exceptionMessage;
    private String exceptionDetailMessage;
}
