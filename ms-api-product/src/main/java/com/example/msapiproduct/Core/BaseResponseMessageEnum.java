package com.example.msapiproduct.Core;

public enum BaseResponseMessageEnum {
    SUCCESS_MESSAGE("İşleminiz Başarıyla gerçekleştirilmiştir."),
    UNSUCCESS_MESSAGE("İşleminiz Gerçekleştirilemedi.");

    private String message;

    BaseResponseMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
