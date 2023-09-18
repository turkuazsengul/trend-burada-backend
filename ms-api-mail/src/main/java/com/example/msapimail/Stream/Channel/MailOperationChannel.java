package com.example.msapimail.Stream.Channel;

import com.example.msapimail.Constant.StreamConstants;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;


@Component
public interface MailOperationChannel {

    @Input(StreamConstants.MAIL_OPERATION_INPUT_CHANNEL)
    MessageChannel inputChannel();
}
