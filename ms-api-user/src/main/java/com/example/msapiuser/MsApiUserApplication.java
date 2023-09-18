package com.example.msapiuser;

import com.example.msapiuser.Stream.Channel.RegisterServiceMailOperationPublishChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableFeignClients
@SpringBootApplication
@EnableBinding({RegisterServiceMailOperationPublishChannel.class})
public class MsApiUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsApiUserApplication.class, args);
    }

}
