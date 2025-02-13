package com.issuemoa.board.infrastructure.kafka;

import com.issuemoa.board.presentation.dto.InquirySaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InquiryProducer {
    private static final String TOPIC_NAME = "issuemoa-topic";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(InquirySaveRequest inquirySaveRequest) {
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, inquirySaveRequest.email(), inquirySaveRequest.contents());

        kafkaTemplate.send(record);

        log.info("Produced message: {}", inquirySaveRequest);
    }
}
