//package com.study.kafkaprac.kafka.controller;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.listener.AcknowledgingMessageListener;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Component;
//
//@Component
//public class KafkaMessageListener implements AcknowledgingMessageListener<Object, Object> {
//
//    @Override
//    @KafkaListener(topics = "${setting.kafka-topic}",
//            groupId = "${setting.kafka-group}",
//            containerFactory = "kafkaConsumerFactory")
//    public void onMessage(ConsumerRecord<Object, Object> data, Acknowledgment acknowledgment) {
//        try {
//            System.out.println(data.toString());
//            acknowledgment.acknowledge();
//            Thread.sleep(3000);
//        } catch (InterruptedException e){
//            System.out.println(e.getMessage());
//        }
//    }
//}
