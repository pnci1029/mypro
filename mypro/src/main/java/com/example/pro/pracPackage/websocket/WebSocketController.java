package com.example.pro.pracPackage.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j @RequiredArgsConstructor
public class WebSocketController {

    private  final WebSocketRepository webSocketRepository;
    @MessageMapping("/hello")
//    @SendTo("/hello")
    public String handleSuccess(@Payload WebSocketRequestDto value) {
        log.info("arrive!");
        log.warn(value.toString());
        ObjectMapper ob = new ObjectMapper();
        WebSocketRequestDto result = ob.convertValue(value, WebSocketRequestDto.class);
//        log.info("result : {}",result.toString());
        WebSocket webSocket = new WebSocket(result.getValue());
        log.info(result.getValue());
        webSocketRepository.save(webSocket);
        return value.toString();
    }

//    @MessageMapping("/channel")
//    @SendTo("/sub/my-channel")
//    public String handleChannel(String message) {
//        System.out.println("Received channel message: " + message);
//        return message;
//    }
}
