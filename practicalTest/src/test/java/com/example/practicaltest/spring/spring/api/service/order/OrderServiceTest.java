package com.example.practicaltest.spring.spring.api.service.order;

import com.example.practicaltest.spring.spring.IntegrationTestSupport;
import com.example.practicaltest.spring.spring.api.controller.order.request.OrderCreateRequest;
import com.example.practicaltest.spring.spring.api.service.order.request.OrderCreateServiceRequest;
import com.example.practicaltest.spring.spring.api.service.order.response.OrderCreateResponse;
import com.example.practicaltest.spring.spring.domain.order.OrderRepository;
import com.example.practicaltest.spring.spring.domain.orderproduct.OrderProductRepository;
import com.example.practicaltest.spring.spring.domain.product.Product;
import com.example.practicaltest.spring.spring.domain.product.ProductRepository;
import com.example.practicaltest.spring.spring.domain.product.ProductSellingType;
import com.example.practicaltest.spring.spring.domain.product.ProductType;
import com.example.practicaltest.spring.spring.domain.stock.Stock;
import com.example.practicaltest.spring.spring.domain.stock.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.practicaltest.spring.spring.domain.product.ProductSellingType.*;
import static com.example.practicaltest.spring.spring.domain.product.ProductType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;

//@ActiveProfiles("test")
//@SpringBootTest
//@DataJpaTest
class OrderServiceTest extends IntegrationTestSupport {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    StockRepository stockRepository;

    @AfterEach
    void tearDown() {

//        productRepository.deleteAll();
        orderProductRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        stockRepository.deleteAllInBatch();
    }

    @DisplayName("상품 번호를 받아서 주문을 생성한다.")
    @Test
    void createOrderTest() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Product product1 = createProduct("아메리카노", "001", SELLING, HANDMADE,2000);
        Product product2 = createProduct("라떼", "002", STOP_SELLING, HANDMADE, 4000);
        Product product3 = createProduct("팥빙수", "003", HOLD, BAKERY, 6000);

        productRepository.saveAll(List.of(product1, product2, product3));


        OrderCreateServiceRequest orderRequest = OrderCreateServiceRequest
                .builder()
                .orderProductNumbers(List.of("001", "003"))
                .build();

        Stock stock1 = Stock.create("001", 4);
        Stock stock2 = Stock.create("002", 4);
        Stock stock3 = Stock.create("003", 4);

        stockRepository.saveAll(List.of(stock1, stock2, stock3));

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

    @DisplayName("재고와 관련된 상품이 포함되어 있는 주문번호를 받아서 주문으로 생성한다.")
    @Test
    void createOrderStockTest() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Product product1 = createProduct("아메리카노", "001", SELLING, BOTTLE,2000);
        Product product2 = createProduct("라떼", "002", SELLING, BAKERY, 4000);
        Product product3 = createProduct("팥빙수", "003", HOLD, HANDMADE, 6000);

        productRepository.saveAll(List.of(product1, product2, product3));

        Stock stock1 = Stock.create("001", 2);
        Stock stock2 = Stock.create("002", 2);

        stockRepository.saveAll(List.of(stock1, stock2));


        OrderCreateServiceRequest orderRequest = OrderCreateServiceRequest
                .builder()
                .orderProductNumbers(List.of("001", "001", "002", "003"))
                .build();

        // when
        OrderCreateResponse orderResponse = orderService.createdOrders(orderRequest, now);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse)
                .extracting("totalPrice", "registeredDateTime")
                .contains(now, 14000);
        assertThat(orderResponse.getProductResponses()).hasSize(4)
                .extracting("name", "price", "sellingType")
                .containsExactlyInAnyOrder(
                        tuple("아메리카노", 2000, SELLING),
                        tuple("아메리카노", 2000, SELLING),
                        tuple("라떼", 4000, SELLING),
                        tuple("팥빙수", 6000, HOLD)
                );


        List<Stock> stocks = stockRepository.findAll();
        assertThat(stocks).hasSize(2)
                .extracting("productNo","quantity")
                .containsExactlyInAnyOrder(
                        tuple("001",2),
                        tuple("002",2)
                );


    }


    @DisplayName("재고가 없는 상품을 주문하면 예외가 발생한다.")
    @Test
    void createOrderNoStockTest() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Product product1 = createProduct("아메리카노", "001", SELLING, BOTTLE,2000);
        Product product2 = createProduct("라떼", "002", SELLING, BAKERY, 4000);
        Product product3 = createProduct("팥빙수", "003", HOLD, HANDMADE, 6000);

        productRepository.saveAll(List.of(product1, product2, product3));

        Stock stock1 = Stock.create("001", 0);
        Stock stock2 = Stock.create("002", 0);

        stockRepository.saveAll(List.of(stock1, stock2));


        OrderCreateServiceRequest orderRequest = OrderCreateServiceRequest
                .builder()
                .orderProductNumbers(List.of("001", "001", "002", "003"))
                .build();

        // when // then
//        OrderCreateResponse orderResponse = orderService.createdOrders(orderRequest, now);

        assertThatThrownBy(() ->orderService.createdOrders(orderRequest, now))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("재고가 부족한 상품이 있습니다. 관리자에게 문의하세요.");
    }


    @DisplayName("중복된 상품 번호로 주문할 수 있다.")
    @Test
    void createOrderDuplicatedProductNumber() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Product product1 = createProduct("아메리카노", "001", SELLING, HANDMADE,2000);
        Product product2 = createProduct("라떼", "002", STOP_SELLING, HANDMADE, 4000);
        Product product3 = createProduct("팥빙수", "003", HOLD, BAKERY, 6000);

        productRepository.saveAll(List.of(product1, product2, product3));


        OrderCreateServiceRequest orderRequest = OrderCreateServiceRequest
                .builder()
                .orderProductNumbers(List.of("001", "001"))
                .build();

        // when
        OrderCreateResponse orderResponse = orderService.createdOrders(orderRequest, now);

        // then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse)
                .extracting("totalPrice", "registeredDateTime")
                .contains(now, 4000);
        assertThat(orderResponse.getProductResponses()).hasSize(2)
                .extracting("name", "price", "sellingType")
                .containsExactlyInAnyOrder(
                        tuple("아메리카노", 2000, SELLING),
                        tuple("아메리카노", 2000, SELLING)
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