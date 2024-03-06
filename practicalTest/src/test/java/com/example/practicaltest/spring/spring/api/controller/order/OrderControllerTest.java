package com.example.practicaltest.spring.spring.api.controller.order;

import com.example.practicaltest.spring.ControllerTestSupport;
import com.example.practicaltest.spring.spring.api.controller.order.request.OrderCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest extends ControllerTestSupport {

    @DisplayName("주문을 생성합니다.")
    @Test
    void createOrders() throws Exception {
        // given
        OrderCreateRequest request = OrderCreateRequest.builder()
                .orderProductNumbers(List.of("001", "002"))
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orders/new")
                .content(om.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.httpStatus").value("OK"))
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.message").value("OK"))
        ;
    }

    @DisplayName("주문 생성 시 상품 번호는 필수입니다.")
    @Test
    void createOrdersWithoutProductNumbers() throws Exception {
        // given
        OrderCreateRequest request = OrderCreateRequest.builder()
                .orderProductNumbers(List.of())
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orders/new")
                        .content(om.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.statusCode").value("400"))
                .andExpect(jsonPath("$.message").value("상품 번호 리스트는 필수입니다."))
                .andExpect(jsonPath("$.data").isEmpty())
        ;
    }

}