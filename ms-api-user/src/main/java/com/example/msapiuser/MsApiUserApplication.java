package com.example.msapiuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableFeignClients
@SpringBootApplication(scanBasePackages={"com.example.msapiuser","com.common.mscommonsecurity"})
public class MsApiUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsApiUserApplication.class, args);
    }

}
