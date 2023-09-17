package com.example.msapiproduct.Core;

public enum BaseResponseCodeEnum {
    SUCCESS_CODE(1111),
    UNSUCCESS_CODE(9999);

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
