package com.example.practicaltest.spring;

import com.example.practicaltest.spring.spring.api.controller.order.OrderController;
import com.example.practicaltest.spring.spring.api.controller.product.ProductController;
import com.example.practicaltest.spring.spring.api.service.order.OrderService;
import com.example.practicaltest.spring.spring.api.service.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@WebMvcTest(controllers = {OrderController.class,
        ProductController.class})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected OrderService orderService;

    @Autowired
    protected ObjectMapper om;

    @MockBean
    protected ProductService productService;
}
