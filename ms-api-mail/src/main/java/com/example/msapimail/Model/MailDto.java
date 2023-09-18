package com.example.msapimail.Model;

import com.example.msapimail.Core.BaseDto;
import lombok.Data;

import java.util.Date;

@Data
public class MailDto extends BaseDto {
    private String fromByMail;
    private String toByMail;
    private String mailSubject;
    private String mailMessageContent;
    private String mailProcessName;
}
