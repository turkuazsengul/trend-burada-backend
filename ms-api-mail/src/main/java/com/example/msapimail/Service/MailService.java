package com.example.msapimail.Service;

import com.example.msapimail.Model.MailDto;

public interface MailService {
    void sendMail(MailDto mailDto);
    void saveMailOutbox(MailDto mailDto);
}
