package com.example.practicaltest.spring.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static com.example.practicaltest.spring.spring.domain.product.ProductType.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductTypeTest {

    // 방법1
    @DisplayName("상품 타입이 재고 관리 타입인 상품이 아니면 false 맞으면 true를 응답한다.")
    @Test
    void productTypeTest() {
        // given
        Product product1 = createProduct(BAKERY);
        Product product2 = createProduct(BOTTLE);
        Product product3 = createProduct(HANDMADE);

        // when
        boolean result1 = containsStockType(product1.getProductType());
        boolean result2 = containsStockType(product2.getProductType());
        boolean result3 = containsStockType(product3.getProductType());

        // then
        assertThat(result1).isTrue();
        assertThat(result2).isTrue();
        assertThat(result3).isFalse();
    }

    // 방법2
    @DisplayName("상품 타입이 재고 관리 타입인 상품이 아니면 false 맞으면 true를 응답한다.")
    @CsvSource({"HANDMADE, false", "BOTTLE,true", "BAKERY,true"})
    @ParameterizedTest
    void containsStockType2(ProductType productType, boolean expected) {
        // when
        boolean result = containsStockType(productType);

        // then
        assertThat(result).isEqualTo(expected);
    }

    // 방법3
    private static Stream<Arguments> provideProductTypesForCheckingStockType() {
        return Stream.of(
                Arguments.of(HANDMADE, false),
                Arguments.of(BAKERY, true),
                Arguments.of(BOTTLE, true)
        );
    }

    @DisplayName("상품 타입이 재고 관리 타입인 상품이 아니면 false 맞으면 true를 응답한다.")
    @MethodSource("provideProductTypesForCheckingStockType")
    @ParameterizedTest
    void containsStockType3(ProductType productType, boolean expected) {
        // when
        boolean result = containsStockType(productType);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private Product createProduct(ProductType productType) {
        return Product.builder()
                .name("상품 이름")
                .productNo("상품 번호")
                .price(3000)
                .sellingType(ProductSellingType.SELLING)
                .productType(productType)
                .build();
    }

}