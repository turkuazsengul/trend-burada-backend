package com.example.apiotomation.Enum;

public enum TestProcessCodeEnum {
    DEFAULT_SUCCESS_CODE(1111),
    DEFAULT_ERROR_CODE(9999),
    ERROR_NO_API_OR_METHOD_TYPE(2222);

    private int code;

    TestProcessCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
