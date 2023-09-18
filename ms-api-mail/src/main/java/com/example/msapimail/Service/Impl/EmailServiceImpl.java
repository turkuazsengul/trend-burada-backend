package com.example.msapimail.Service.Impl;

import com.example.msapimail.Converter.MailConverter;
import com.example.msapimail.Model.MailDto;
import com.example.msapimail.Repository.OutBoxMailRepository;
import com.example.msapimail.Service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements MailService {
    private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private OutBoxMailRepository outBoxMailRepository;

    @Autowired
    private MailConverter mailConverter;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(MailDto mailDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        try {
            createRegisterConfirmMessage(mailDto, simpleMailMessage);
            javaMailSender.send(simpleMailMessage);

        } catch (Exception e) {
            logger.error( mailDto.getMailProcessName() + " Process Fail: " + e);
        }
    }

    @Override
    public void saveMailOutbox(MailDto mailDto) {
        try{
            outBoxMailRepository.save(mailConverter.convertToEntity(mailDto));
        }catch (Exception e){
            logger.error("Mail Save Process Fail: " + e);
        }
    }


    public void createRegisterConfirmMessage(MailDto mailDto, SimpleMailMessage message) {
        message.setFrom(mailDto.getFromByMail());
        message.setTo(mailDto.getToByMail());
        message.setSubject(mailDto.getMailSubject());
        message.setText(mailDto.getMailMessageContent());
    }
}
