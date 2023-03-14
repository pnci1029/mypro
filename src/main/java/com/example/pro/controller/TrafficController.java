package com.example.pro.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TrafficController {

    @GetMapping("/cpu")
    public String cpuIssue() {
        log.error("log");
        long value = 0;
        Map<String, String> map = new HashMap<>();
        Map<String, String> maps = new HashMap<>();


        for (long i = 0; i < 10000000000L; i++) {
            value++;
        }
        return "value" + value;
    }

    List<Integer> arr = new ArrayList<>();
    @GetMapping("/jvm")
    public String jvmIssue() {
        for (int i = 0; i < 1000000; i++) {
            arr.add(i);
        }
        return "jvm + " +arr.size();
    }
}
