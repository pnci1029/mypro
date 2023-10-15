package com.study.kafkaprac.kafka.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/kafka")
@AllArgsConstructor
@Slf4j
public class kafkaMessageController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final NewTopic myTopic1;
    private final NewTopic myTopic2;

    @GetMapping("/publish/mytopic1")
    public String publishSpringTopic1() {

        String message = "publish message to my_topic_1 " + UUID.randomUUID();

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("my_topic_1", message);
        log.info(myTopic1.name());
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
        return "done";
    }

    @GetMapping("/publish/mytopic2")
    public String publish() {

        String message = "publish message to my_topic_2 " + UUID.randomUUID();

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(myTopic2.name(), message);
        log.info(myTopic2.name());
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
        return "done";
    }
}