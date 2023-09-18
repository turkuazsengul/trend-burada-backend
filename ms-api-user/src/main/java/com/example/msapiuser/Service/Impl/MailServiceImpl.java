package com.example.msapiuser.Service.Impl;

import com.example.msapiuser.Model.MailDto;
import com.example.msapiuser.Service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final ApplicationEventPublisher applicationEventPublisher;

    public MailServiceImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void sendRegisterConfirmMail2Queues(String to, int confirmCode){
        MailDto mailDto = new MailDto();
        try {
            mailDto.setFromByMail("trend-burada@outlook.com");
            mailDto.setToByMail(to);
            mailDto.setMailSubject(" Trend Burada Register Confirm");
            mailDto.setMailMessageContent("Trend Burada Ailesine Hoş Geldiniz\n\nUygulama Kayıt Onay Kodunuz: " + confirmCode);
            mailDto.setMailProcessName("RegisterConfirmMail");

            applicationEventPublisher.publishEvent(mailDto);
            logger.info("Mail sent to Q. Mail Detail: {Adress:"+mailDto.getToByMail()+"}");
        } catch (Exception e) {
            logger.error("Q fail : " + e);
        }
    }
}
