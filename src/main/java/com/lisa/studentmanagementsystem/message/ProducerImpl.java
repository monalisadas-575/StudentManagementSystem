package com.lisa.studentmanagementsystem.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class ProducerImpl<PAYLOAD> {

    private PAYLOAD payload;
    @Autowired
    private StreamBridge streamBridge;
    public void send(String bindingName,PAYLOAD payload){
        streamBridge.send(bindingName,payload);
    }
}
