package com.study.kafkaprac.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service @Slf4j
public class KafkaConsumerController {

    @KafkaListener(topics = "my-topic_test",groupId = "myKafka")
    public void kafkaListener(@Payload String message,
                              @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("message : {}",message);
    }

}
