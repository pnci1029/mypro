package com.example.practicaltest.spring.spring.api.controller.product;

import com.example.practicaltest.spring.spring.api.controller.product.dto.request.ProductCreateRequest;
import com.example.practicaltest.spring.spring.api.service.product.ProductService;
import com.example.practicaltest.spring.spring.domain.product.ProductSellingType;
import com.example.practicaltest.spring.spring.domain.product.ProductType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.example.practicaltest.spring.spring.domain.product.ProductSellingType.SELLING;
import static com.example.practicaltest.spring.spring.domain.product.ProductType.HANDMADE;

/**
 * SpringBootTest 어노테이션은 전체 bean context를 띄우는데 반해
 * WebMvctest 어노테이션은 컨트롤러 레이어만 뗴어내서 테스트 하기 위함
 * MockMvc 어노테이션과 함께 사용
 */
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    /**
     * service 레이어 하위는 모두 mocking 처리
     * 이를 위한 MockMvc 의존성 주입
     */
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    // 요청 시 직렬화/역직렬화를 위함
    @Autowired
    ObjectMapper om;


    @DisplayName("상품을 등록한다.")
    @Test
    void createProduct() throws Exception {
        ProductCreateRequest request = ProductCreateRequest.builder()
                .name("아메리카노")
                .productType(HANDMADE)
                .sellingType(SELLING)
                .price(3000)
                .build();
        // given

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product/new")
                        .content(om.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}