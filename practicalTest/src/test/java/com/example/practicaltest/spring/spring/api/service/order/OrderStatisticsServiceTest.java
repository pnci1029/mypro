package com.example.practicaltest.spring.spring.api.service.order;

import com.example.practicaltest.spring.spring.client.mail.MailSendClient;
import com.example.practicaltest.spring.spring.domain.history.mail.MailSendHistory;
import com.example.practicaltest.spring.spring.domain.history.mail.MailSendHistoryRepository;
import com.example.practicaltest.spring.spring.domain.order.Order;
import com.example.practicaltest.spring.spring.domain.order.OrderRepository;
import com.example.practicaltest.spring.spring.domain.order.OrderStatus;
import com.example.practicaltest.spring.spring.domain.orderproduct.OrderProductRepository;
import com.example.practicaltest.spring.spring.domain.product.Product;
import com.example.practicaltest.spring.spring.domain.product.ProductRepository;
import com.example.practicaltest.spring.spring.domain.product.ProductSellingType;
import com.example.practicaltest.spring.spring.domain.product.ProductType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.practicaltest.spring.spring.domain.product.ProductType.BAKERY;
import static com.example.practicaltest.spring.spring.domain.product.ProductType.HANDMADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class OrderStatisticsServiceTest {

    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderStatisticsService orderStatisticsService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MailSendHistoryRepository mailSendHistoryRepository;

    @MockBean
    private MailSendClient mailSendClient;


    @AfterEach
    void tearDown() {
        orderProductRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        mailSendHistoryRepository.deleteAllInBatch();
    }

    @DisplayName("결제 완료 주문들을 조회하여 매출 통계 매일을 전송한다.")
    @Test
    void sendOrderStatisticsMail() {
        // given
        LocalDateTime now = LocalDateTime.of(2024, 3, 4, 0, 0);
        Product product1 = createProduct("001", HANDMADE, 1000);
        Product product2 = createProduct("002", BAKERY, 3000);
        Product product3 = createProduct("003", HANDMADE, 5000);

        List<Product> productList = List.of(product1, product2, product3);
        productRepository.saveAll(productList);

        Order order1 = createPaymentCompletedOrder(LocalDateTime.of(2024, 3, 3,23,59), productList);
        Order order2 = createPaymentCompletedOrder(now, productList);
        Order order3 = createPaymentCompletedOrder(LocalDateTime.of(2024, 3, 4,23,59), productList);
        Order order4 = createPaymentCompletedOrder(LocalDateTime.of(2024, 3, 5,0,0), productList);

        // stubbing
        Mockito.when(mailSendClient.sendEmail(any(String.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(true)
        ;

        // when
        boolean result = orderStatisticsService.sendOrderStatisticsMail(LocalDate.of(2024, 3, 4), "test@gmail.com");

        // then
        assertThat(result).isTrue();

        List<MailSendHistory> histories = mailSendHistoryRepository.findAll();
        assertThat(histories).hasSize(1)
                .extracting("content")
                .contains("총 매출 합계는 18000원입니다.");
    }

    private Order createPaymentCompletedOrder(LocalDateTime now, List<Product> productList) {
        Order order = Order.builder()
                .productList(productList)
                .registeredDateTime(now)
                .orderStatus(OrderStatus.COMPLETED)
                .build();
        System.out.println(order.getOrderStatus());
        return orderRepository.save(order);
    }

    private Product createProduct(String productNo, ProductType productType, int price) {
        return Product.builder()
                .name("아메리카노")
                .productNo(productNo)
                .price(price)
                .sellingType(ProductSellingType.SELLING)
                .productType(productType)
                .build();
    }

}