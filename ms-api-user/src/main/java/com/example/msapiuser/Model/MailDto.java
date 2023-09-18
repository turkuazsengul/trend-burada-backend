package com.example.msapiuser.Model;

import lombok.Data;

import java.util.Date;

@Data
public class MailDto{
    private String fromByMail;
    private String toByMail;
    private String mailSubject;
    private String mailMessageContent;
    private String mailProcessName;
}
