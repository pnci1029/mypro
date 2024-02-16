package com.example.practicaltest.spring.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.practicaltest.spring.spring.domain.product.ProductSellingType.*;
import static com.example.practicaltest.spring.spring.domain.product.ProductType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @DisplayName("원하는 판매 가능한 상품을 조회한다.")
    @Test
    void findAllBySellingTypeIn() {
        // given
        Product product1 = Product.builder()
                .name("아메리카노")
                .productNo("001")
                .price(3000)
                .sellingType(SELLING)
                .productType(BOTTLE)
                .build();

        Product product2 = Product.builder()
                .name("라떼")
                .productNo("002")
                .price(3500)
                .sellingType(SELLING)
                .productType(HANDMADE)
                .build();

        Product product3 = Product.builder()
                .name("팥빙수")
                .productNo("003")
                .price(6000)
                .sellingType(STOP_SELLING)
                .productType(HANDMADE)
                .build();

        productRepository.saveAll(List.of(product1, product2, product3));

        // when
        List<Product> targetProducts = productRepository.findAllBySellingTypeIn(List.of(SELLING, HOLD));

        // then
        assertThat(targetProducts).hasSize(2)
                .extracting("name", "productNo", "sellingType")
                .containsExactlyInAnyOrder(
                        tuple("아메리카노", "001", SELLING),
                        tuple("라떼", "002", SELLING)
                );
    }

}