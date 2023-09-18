package com.example.msapiuser.Stream.Channel;

import com.example.msapiuser.Constants.StreamConstants;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface RegisterServiceMailOperationPublishChannel {

    @Output(StreamConstants.MAIL_OPERATION_OUTPUT_CHANNEL)
    MessageChannel outputChannel();
}
