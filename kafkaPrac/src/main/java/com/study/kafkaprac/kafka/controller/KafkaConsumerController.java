package com.study.kafkaprac.kafka.controller;

import com.study.kafkaprac.kafka.domain.entity.Channel;
import com.study.kafkaprac.kafka.domain.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class KafkaConsumerController {

    @KafkaListener(topics = "my_topic_1", groupId = "foo")
    public void consumeMyopic1(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition){
        System.out.println("success11");
        log.info("[consume message]: {} from partition: {}", message, partition);
    }

    @KafkaListener(topics = "#{myTopic2.name}", groupId = "group1")
    public void consumeMyopic2(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition){
        System.out.println("success222");
        log.info("[consume message]: {} from partition: {}", message, partition);
    }
}
