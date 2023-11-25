package com.challeng.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessageService {


    @Value("${topic.result-messages}")
    private String topicResultPautMessages;

    private final KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String message){
            this.kafkaTemplate.send(topicResultPautMessages, message);
    }
}
