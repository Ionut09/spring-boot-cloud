package com.gohenry.boot.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    String INPUT = "singer-input";

    String OUTPUT = "singer-output";

    @Output(OUTPUT)
    MessageChannel singerOutputChannel();

    @Input(INPUT)
    SubscribableChannel singerInputChannel();
}
