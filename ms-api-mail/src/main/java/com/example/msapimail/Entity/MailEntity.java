package com.example.msapimail.Entity;

import com.example.msapimail.Core.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "outbox_mail_items")
public class MailEntity extends BaseEntity {

    @Column(name = "from_by_mail")
    private String fromByMail;

    @Column(name = "to_by_mail")
    private String toByMail;

    @Column(name = "subject_mail")
    private String mailSubject;

    @Column(name = "message_content_mail")
    private String mailMessageContent;

    @Column(name = "process_name_mail")
    private String mailProcessName;

}
