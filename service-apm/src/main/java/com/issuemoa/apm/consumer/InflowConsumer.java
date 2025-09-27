package com.issuemoa.apm.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InflowConsumer {
    private static final String TOPIC_NAME = "issuemoa-inflow";
    @KafkaListener(topics = TOPIC_NAME)
    public void listen(ConsumerRecord<String, String> record) {
        String key = record.key();
        String value = record.value();
        log.info("Consumer - Key: " + key + ", Value: " + value);
    }
}
