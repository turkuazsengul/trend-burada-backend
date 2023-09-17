package com.example.msapiproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsApiProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsApiProductApplication.class, args);
    }

}
