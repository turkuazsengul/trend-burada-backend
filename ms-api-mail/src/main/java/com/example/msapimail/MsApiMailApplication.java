package com.example.msapimail;

import com.example.msapimail.Stream.Channel.MailOperationChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding({
        MailOperationChannel.class
})
@SpringBootApplication
public class MsApiMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsApiMailApplication.class, args);
    }

}
