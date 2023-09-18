package com.example.msapiuser.Core.Response;

public enum BaseResponseCodeEnum {
    SUCCESS_CODE(99),
    UNSUCCESS_CODE(11);

    private int code;

    BaseResponseCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
