package com.example.practicaltest.spring.spring.api.service.order;

import com.example.practicaltest.spring.spring.api.controller.order.request.OrderCreateRequest;
import com.example.practicaltest.spring.spring.api.service.order.response.OrderCreateResponse;
import com.example.practicaltest.spring.spring.domain.product.Product;
import com.example.practicaltest.spring.spring.domain.product.ProductRepository;
import com.example.practicaltest.spring.spring.domain.product.ProductSellingType;
import com.example.practicaltest.spring.spring.domain.product.ProductType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.practicaltest.spring.spring.domain.product.ProductSellingType.*;
import static com.example.practicaltest.spring.spring.domain.product.ProductType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@SpringBootTest
//@DataJpaTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductRepository productRepository;

    @DisplayName("상품 번호를 받아서 주문을 생성한다.")
    @Test
    void createOrderTest() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Product product1 = createProduct("아메리카노", "001", SELLING, HANDMADE,2000);
        Product product2 = createProduct("라떼", "002", STOP_SELLING, HANDMADE, 4000);
        Product product3 = createProduct("팥빙수", "003", HOLD, BAKERY, 6000);

        productRepository.saveAll(List.of(product1, product2, product3));


        OrderCreateRequest orderRequest = OrderCreateRequest
                .builder()
                .orderProductNumbers(List.of("001", "003"))
                .build();

        // when
        OrderCreateResponse orderResponse = orderService.createdOrders(orderRequest, now);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse)
                .extracting("totalPrice", "registeredDateTime")
                .contains(now, 8000);
        assertThat(orderResponse.getProductResponses()).hasSize(2)
                .extracting("name", "price", "sellingType")
                .containsExactlyInAnyOrder(
                        tuple("아메리카노", 2000, SELLING),
                        tuple("팥빙수", 6000, HOLD)
                );
    }


    private Product createProduct(String name, String productNo, ProductSellingType sellingType, ProductType productType, int price) {
        return Product.builder()
                .name(name)
                .productNo(productNo)
                .price(price)
                .sellingType(sellingType)
                .productType(productType)
                .build();
    }
}