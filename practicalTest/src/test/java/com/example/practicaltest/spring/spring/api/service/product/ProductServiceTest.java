package com.example.practicaltest.spring.spring.api.service.product;

import com.example.practicaltest.spring.spring.api.controller.product.dto.request.ProductCreateRequest;
import com.example.practicaltest.spring.spring.api.service.product.response.ProductResponse;
import com.example.practicaltest.spring.spring.domain.product.Product;
import com.example.practicaltest.spring.spring.domain.product.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.example.practicaltest.spring.spring.domain.product.ProductSellingType.SELLING;
import static com.example.practicaltest.spring.spring.domain.product.ProductType.BAKERY;
import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

    @DisplayName("상품을 등록합니다. 상품번호는 가장 최근에 등록된 상품 번호 +1 입니다.")
    @Test
    void createProduct() {
        // given
        Product product1 = createProduct("001");
        Product product2 = createProduct("002");
        Product product3 = createProduct("003");

        productRepository.saveAll(List.of(product1, product2, product3));

        ProductCreateRequest request = ProductCreateRequest.of(BAKERY, SELLING, "카푸치노", 3000);

        // when
        ProductResponse response = productService.createProduct(request);

        // then
        assertThat(response)
                .extracting("productNo", "name")
                .contains("004", "카푸치노");
    }

    @DisplayName("등록 상품이 없는 경우에서 상품을 등록합니다. 상품번호는 001 입니다.")
    @Test
    void createProductWithoutProductLeft() {
        // given
        ProductCreateRequest request = ProductCreateRequest.of(BAKERY, SELLING, "카푸치노", 3000);

        // when
        ProductResponse response = productService.createProduct(request);

        // then
        assertThat(response)
                .extracting("productNo", "name")
                .contains("001", "카푸치노");
    }

    private Product createProduct(String productNo) {
        return Product.builder()
                .name("아메리카노")
                .productNo(productNo)
                .price(3000)
                .sellingType(SELLING)
                .productType(BAKERY)
                .build();
    }
}