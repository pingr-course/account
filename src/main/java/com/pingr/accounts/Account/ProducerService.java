package com.pingr.accounts.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    @Value(value = "${topic.accounts}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessage(Account message) {
        this.template.send(this.topic, message);
    }
}

