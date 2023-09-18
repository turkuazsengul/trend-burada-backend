package com.example.msapimail.Converter;

import com.example.msapimail.Core.AbstractBaseConverter;
import com.example.msapimail.Entity.MailEntity;
import com.example.msapimail.Model.MailDto;
import org.springframework.stereotype.Component;

@Component
public class MailConverter extends AbstractBaseConverter<MailDto, MailEntity> {
}
