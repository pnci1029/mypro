package com.example.practicaltest.spring.spring.domain.order;

import com.example.practicaltest.spring.spring.domain.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.practicaltest.spring.spring.domain.order.OrderStatus.INIT;
import static com.example.practicaltest.spring.spring.domain.product.ProductSellingType.HOLD;
import static com.example.practicaltest.spring.spring.domain.product.ProductType.HANDMADE;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
//@SpringBootTest
@DataJpaTest
class OrderTest {

    @DisplayName("주문 생성 시 상태는 INIT이다")
    @Test
    void init() {
        // given
        List<Product> product = List.of(
                createProduct("001", 2000),
                createProduct("002", 4000),
                createProduct("003", 7000)
        );

        // when
        Order order = Order.create(product, LocalDateTime.now());

        // then
        assertThat(order.getOrderStatus()).isEqualTo(INIT);
    }

    @DisplayName("상품 생성 시 전체 금액을 계산한다.")
    @Test
    void calculateTotalPrice() {
        // given
        List<Product> product = List.of(
                createProduct("001", 2000),
                createProduct("002", 4000),
                createProduct("003", 7000)
        );

        // when
        Order order = Order.create(product, LocalDateTime.now());

        // then
        assertThat(order.getTotalPrice()).isEqualTo(13000);
    }

    @DisplayName("상품 생성 시 현재시간을 기록한다.")
    @Test
    void registeredTime() {
        LocalDateTime registeredDateTime = LocalDateTime.now();
        List<Product> product = List.of(
                createProduct("001", 2000),
                createProduct("002", 4000),
                createProduct("003", 7000)
        );

        // when
        Order order = Order.create(product, registeredDateTime);

        // then
        assertThat(order.getRegisteredDateTime()).isEqualTo(registeredDateTime);
    }

    private Product createProduct(String productNo, int price) {
        return Product.builder()
                .name("상품 이름")
                .productNo(productNo)
                .price(price)
                .sellingType(HOLD)
                .productType(HANDMADE)
                .build();
    }

}