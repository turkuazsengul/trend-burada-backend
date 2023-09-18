package com.example.msapiuser.Stream.Listener;

import com.example.msapiuser.Model.MailDto;
import com.example.msapiuser.Stream.Channel.RegisterServiceMailOperationPublishChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceMailOperationEventListener {

    @Autowired
    RegisterServiceMailOperationPublishChannel mailOperationPublishChannel;

    @EventListener(MailDto.class)
    public void onCommonDataChanged(MailDto mailDto){
        mailOperationPublishChannel.outputChannel().send(MessageBuilder.withPayload(mailDto).build());
    }
}
