package com.example.pro.pracPackage.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j @RequiredArgsConstructor
public class WebSocketController {

    private  final WebSocketRepository webSocketRepository;
    @MessageMapping("/hello")
//    @SendTo("/hello")
    public String handleSuccess(@RequestParam(name = "value")String value) {
        log.info("arrive!");
        log.error(value);
        ObjectMapper ob = new ObjectMapper();
        WebSocketRequestDto result = ob.convertValue(value, WebSocketRequestDto.class);
        WebSocket webSocket = new WebSocket(result.getValue());
        log.info(result.getValue());
        webSocketRepository.save(webSocket);
        return value;
    }

//    @MessageMapping("/channel")
//    @SendTo("/sub/my-channel")
//    public String handleChannel(String message) {
//        System.out.println("Received channel message: " + message);
//        return message;
//    }
}
