package com.example.practicaltest.spring.spring.domain.product;

import com.example.practicaltest.spring.spring.domain.stock.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.example.practicaltest.spring.spring.domain.product.ProductSellingType.*;
import static com.example.practicaltest.spring.spring.domain.product.ProductType.BOTTLE;
import static com.example.practicaltest.spring.spring.domain.product.ProductType.HANDMADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@ActiveProfiles("test")
//@SpringBootTest
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @DisplayName("원하는 판매 가능한 상품을 조회한다.")
    @Test
    void findAllBySellingTypeIn() {
        // given
        Product product1 = createProduct("아메리카노", "001", 3000, SELLING, BOTTLE);
        Product product2 = createProduct("라떼", "002", 3500, SELLING, HANDMADE);
        Product product3 = createProduct("팥빙수", "003", 6000, STOP_SELLING, HANDMADE);

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


    @DisplayName("상품 id를 가지고 상품들을 조회한다.")
    @Test
    void findAllByProductNoIn() {
        // given
        Product product1 = createProduct("아메리카노", "001", 3000, SELLING, BOTTLE);
        Product product2 = createProduct("라떼", "002", 3500, SELLING, HANDMADE);
        Product product3 = createProduct("팥빙수", "003", 6000, STOP_SELLING, HANDMADE);

        productRepository.saveAll(List.of(product1, product2, product3));

        // when
        List<Product> targetProductList = productRepository.findAllByProductNoIn(List.of("003", "002"));

        // then
        assertThat(targetProductList).hasSize(2)
                .extracting("name", "productNo", "sellingType")
                .containsExactlyInAnyOrder(
                        tuple("라떼", "002", SELLING),
                        tuple("팥빙수", "003", STOP_SELLING)
                );
    }


    @DisplayName("가장 마지막에 생성된 상품 번호를 조회합니다.")
    @Test
    void findLatestProductNo() {
        // given
        String targetProductNo = "003";
        Product product1 = createProduct("아메리카노", "001", 3000, SELLING, BOTTLE);
        Product product2 = createProduct("라떼", "002", 3500, SELLING, HANDMADE);
        Product product3 = createProduct("팥빙수", targetProductNo, 6000, STOP_SELLING, HANDMADE);

        productRepository.saveAll(List.of(product1, product2, product3));

        // when
        String latestProductNo = productRepository.findLatestProduct();

        // then
        assertThat(latestProductNo).isEqualTo(targetProductNo);
    }

    @DisplayName("아무 상품이 등록되지 않은 상태에서 상품을 등록할 경우 null을 반환한다.")
    @Test
    void findLatestProductNoWithoutData() {
        // given

        // when
        String latestProductNo = productRepository.findLatestProduct();

        // then
        assertThat(latestProductNo).isNull();
    }

    private Product createProduct(String name,String productNo, int price, ProductSellingType sellingType, ProductType productType) {
        return Product.builder()
                .name(name)
                .productNo(productNo)
                .price(price)
                .sellingType(sellingType)
                .productType(productType)
                .build();
    }

}