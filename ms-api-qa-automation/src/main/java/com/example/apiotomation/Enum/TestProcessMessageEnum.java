package com.example.apiotomation.Enum;

public enum TestProcessMessageEnum {
    SUCCESS_AUTOMATION_TEST_MESSAGE("Test süreci başarıyla tamamlandı."),
    SUCCESS__MESSAGE("İşleminiz başarıyla gerçekleştirildi"),
    ERROR__MESSAGE("İşleminiz gerçekleştirilirken bir hata oluştu !"),
    ERROR_REQUEST_METHOD_TYPE("Method tipleri uygun değil"),
    ERROR_CHECK_DATA_LIST("Data Listesi eşleşmiyor !"),
    SUCCESS_CHECK_DATA_LIST("Data listesi başarılı şekilde eşleşti."),
    SUCCESS_WITH_NO_CHECK_DATA_LIST("Data listesi eşleştirme testi koşulmadı. Servis cevap dönüşü 200"),
    ERROR_NO_METHOD_TYPE_OR_URL("Service'e ait url alanı ve method tip bilgisi boş olamaz !"),
    REQUEST_METHOD_TYPE_GET("GET"),
    REQUEST_METHOD_TYPE_POST("POST"),
    REQUEST_METHOD_TYPE_PUT("PUT"),
    REQUEST_METHOD_TYPE_DELETE("DELETE"),
    SUCCESS_RUN_TEST_RESULT("PASS"),
    ERROR_RUN_TEST_RESULT("FAIL");

    private String message;

    TestProcessMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
