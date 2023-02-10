package com.example.pro.controller;

import com.example.pro.domain.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
public class ArticleController {

    MockMvc mock;
    @Autowired
    HelloController hello;
    @Test
    void articleBasicTest() {

    }
}
