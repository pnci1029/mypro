package com.study.kafkaprac.kafka.controller;

import com.study.kafkaprac.kafka.domain.entity.Channel;
import com.study.kafkaprac.kafka.domain.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
@Slf4j @RequiredArgsConstructor
public class KafkaConsumerController {
    private final ChannelRepository channelRepository;

    @KafkaListener(topics = {"my-topic_test"},groupId = "myKafka")
    public void kafkaListener(@Payload String message,
                              @Header(KafkaHeaders.RECEIVED_TOPIC) int topic,
                              @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("message : {}",message);
        log.info("topic : {}",topic);


        channelRepository.save(Channel.builder()
                .topicName("my-topic_test")
                .message(message)
                .build());
        log.info(String.valueOf(channelRepository.findAll().size()));
    }

}
