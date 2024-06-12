package com.example.practicaltest.spring.spring.domain.order;

import com.example.practicaltest.spring.spring.IntegrationTestSupport;
import com.example.practicaltest.spring.spring.domain.product.Product;
import com.example.practicaltest.spring.spring.domain.product.ProductRepository;
import com.example.practicaltest.spring.spring.domain.product.ProductSellingType;
import com.example.practicaltest.spring.spring.domain.product.ProductType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class OrderRepositoryTest extends IntegrationTestSupport {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @DisplayName("오늘 결제된 주문 중 결제 완료 주문을 조회한다.")
    @Test
    void findOrdersBy() {
        // given
        LocalDateTime now = LocalDateTime.now();
        LocalDate nowDate = now.toLocalDate();
        Product product1 = createProduct("001");
        Product product2 = createProduct("002");
        Product product3 = createProduct("003");

        productRepository.saveAll(List.of(product1, product2, product3));

        Order order = Order.create(List.of(product1, product2, product3), now);
        orderRepository.save(order);

        // when
        OrderStatus expect = OrderStatus.INIT;
        List<Order> orderResult = orderRepository.findOrdersBy(
                nowDate.atStartOfDay(),
                nowDate.plusDays(1).atStartOfDay(),
                expect
        );

        // then
        assertThat(orderResult).hasSize(1)
                .extracting("registeredDateTime", "totalPrice")
                .containsExactlyInAnyOrder(
                        tuple(now, 9000)
                )
        ;
    }

    private Product createProduct(String productNo) {
        return Product.builder()
                .productNo(productNo)
                .productType(ProductType.BAKERY)
                .sellingType(ProductSellingType.SELLING)
                .price(3000)
                .name("아메리카노")
                .build();
    }


}