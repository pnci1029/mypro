package com.example.pro.pracPackage.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j
public class WebSocketController {

    @MessageMapping("/pub/success")
    @SendTo("/sub/hello")
    public String handleSuccess(@RequestParam(name = "value") String value) {
        System.out.println("Received success message: " + value);
        log.error(value);
        return value;
    }

//    @MessageMapping("/channel")
//    @SendTo("/sub/my-channel")
//    public String handleChannel(String message) {
//        System.out.println("Received channel message: " + message);
//        return message;
//    }
}
