package com.example.practicaltest.spring.spring.api.controller.product;

import com.example.practicaltest.spring.ControllerTestSupport;
import com.example.practicaltest.spring.spring.api.controller.product.dto.request.ProductCreateRequest;
import com.example.practicaltest.spring.spring.api.service.product.ProductService;
import com.example.practicaltest.spring.spring.api.service.product.response.ProductResponse;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.example.practicaltest.spring.spring.domain.product.ProductSellingType.SELLING;
import static com.example.practicaltest.spring.spring.domain.product.ProductType.HANDMADE;
import static org.mockito.Mockito.when;

/**
 * SpringBootTest 어노테이션은 전체 bean context를 띄우는데 반해
 * WebMvctest 어노테이션은 컨트롤러 레이어만 뗴어내서 테스트 하기 위함
 * MockMvc 어노테이션과 함께 사용
 */
class ProductControllerTest extends ControllerTestSupport {

    /**
     * service 레이어 하위는 모두 mocking 처리
     * 이를 위한 MockMvc 의존성 주입
     */

    // 요청 시 직렬화/역직렬화를 위함
//    @Autowired
//    ObjectMapper om;


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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    
    @DisplayName("신규 상품을 등록할 때 상품 타입은 필수이다.")
    @Test
    void createProductWithoutProductType() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .sellingType(SELLING)
                .price(3000)
                .name("아메리카노")
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product/new")
                        .content(om.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("상품 타입은 필수입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty())
        ;
    }

    @DisplayName("신규 상품을 등록할 때 판매 타입은 필수이다.")
    @Test
    void createProductWithoutSellingType() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .productType(HANDMADE)
                .price(3000)
                .name("아메리카노")
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product/new")
                        .content(om.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("상품 판매 타입은 필수입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty())
        ;
    }

    @DisplayName("신규 상품을 등록할 때 상품명 기입은 필수이다.")
    @Test
    void createProductWithoutProductName() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .productType(HANDMADE)
                .sellingType(SELLING)
                .price(3000)
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product/new")
                        .content(om.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("상품 이름은 필수입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty())
        ;
    }


    @DisplayName("신규 상품을 등록할 때 가격은 필수이다.")
    @Test
    void createProductWithoutPrice() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .productType(HANDMADE)
                .sellingType(SELLING)
                .name("아메리카노")
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product/new")
                        .content(om.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("상품 가격은 양수여야 합니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty())
        ;
    }

    @DisplayName("주문하려는 상품을 조회한다.")
    @Test
    void getSellingProducts() throws Exception {
        // given

        List<ProductResponse> result = List.of();
        when(productService.getSellingProducts()).thenReturn(result);

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/selling")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.httpStatus").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
        ;
    }

}