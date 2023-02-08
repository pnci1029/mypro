package com.example.pro.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class HelloControllerTest {

    MockMvc mock;
    @Autowired
    HelloController hello;

    @Test
    void helloTest() throws Exception {
        MockMvc build = MockMvcBuilders.standaloneSetup(hello).build();


        build.perform(
                        //get 요청
                        MockMvcRequestBuilders.get("/test")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200));

    }


}
