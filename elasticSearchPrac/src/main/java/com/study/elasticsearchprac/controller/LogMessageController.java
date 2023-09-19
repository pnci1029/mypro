package com.study.elasticsearchprac.controller;

import com.study.elasticsearchprac.service.LogMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller @RequiredArgsConstructor
public class LogMessageController {
    private final LogMessageService logMessageService;


    @GetMapping("/log-message/search")
    public String searchMessage(@RequestParam(name = "keyword") String keyword,
                                Model model) {
        model.addAttribute("message", logMessageService.searchMessage(keyword));
        return "searchLog";
    }
}


