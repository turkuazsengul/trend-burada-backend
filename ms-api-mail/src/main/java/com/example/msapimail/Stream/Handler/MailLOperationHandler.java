package com.example.msapimail.Stream.Handler;

import com.example.msapimail.Constant.StreamConstants;
import com.example.msapimail.Model.MailDto;
import com.example.msapimail.Service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class MailLOperationHandler {
    private final Logger logger = LoggerFactory.getLogger(MailLOperationHandler.class);

    @Autowired
    private MailService mailService;

    @StreamListener(StreamConstants.MAIL_OPERATION_INPUT_CHANNEL)
    public void handleMessage(MailDto mailDto) {
        logger.info("Handled mail operation message that {}", mailDto);

        if (mailDto.getFromByMail() != null && mailDto.getToByMail() != null && mailDto.getMailMessageContent() != null
                && mailDto.getMailSubject() != null && mailDto.getMailProcessName() != null) {

            mailService.saveMailOutbox(mailDto);
            mailService.sendMail(mailDto);
        } else {
            throw new RuntimeException("Mail Operation Exception!");
        }
    }

    @RabbitListener(queues = StreamConstants.MAIL_OPERATION_DEAD_LETTER_QUEUE)
    public void handleDLQMessage(Message failedMessage) {
        logger.error("Unhandled mail operation message that {}", failedMessage);
    }
}
