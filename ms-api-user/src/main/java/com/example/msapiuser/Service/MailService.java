package com.example.msapiuser.Service;

import org.springframework.scheduling.annotation.Async;

public interface MailService {
    @Async
    void sendRegisterConfirmMail2Queues(String to, int confirmCode);
}
