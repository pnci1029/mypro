package com.example.practicaltest.spring.spring.api.service.order;

import com.example.practicaltest.spring.spring.api.controller.order.request.OrderCreateRequest;
import com.example.practicaltest.spring.spring.api.service.order.response.OrderCreateResponse;
import com.example.practicaltest.spring.spring.domain.order.Order;
import com.example.practicaltest.spring.spring.domain.order.OrderRepository;
import com.example.practicaltest.spring.spring.domain.order.OrderStatus;
import com.example.practicaltest.spring.spring.domain.product.Product;
import com.example.practicaltest.spring.spring.domain.product.ProductRepository;
import com.example.practicaltest.spring.spring.domain.product.ProductType;
import com.example.practicaltest.spring.spring.domain.stock.Stock;
import com.example.practicaltest.spring.spring.domain.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;


    public OrderCreateResponse createdOrders(OrderCreateRequest orderCreateRequest, LocalDateTime registeredDateTime) {
        List<String> orderNumbers = orderCreateRequest.getOrderProductNumbers();
        List<Product> collectsResult = findOrderProductNumbers(orderNumbers);

        List<String> stockTypeProductList = collectsResult.stream()
                .filter(items -> ProductType.containsStockType(items.getProductType()))
                .map(Product::getProductNo)
                .collect(Collectors.toList());

        // 재고 조회
        List<Stock> stocks = stockRepository.findAllByProductNoIn(stockTypeProductList);
        Map<String, Stock> stockMap = stocks.stream()
                .collect(Collectors.toMap(Stock::getProductNo, stock -> stock));

        // 상품별 재고 카운팅
        Map<String, Long> stockCountingMap = stocks.stream()
                .collect(Collectors.groupingBy(Stock::getProductNo, Collectors.counting()));

        //
        for (String productNo : stockTypeProductList) {
            Stock stock = stockMap.get(productNo);
            int quantity = stockCountingMap.get(productNo).intValue();

            if (stock.isDecreasable(quantity)) {
                throw new IllegalArgumentException("재고가 부족한 상품이 있습니다. 관리자에게 문의하세요.");
            }
            stock.decrease(quantity);
        }


        Order order = Order.create(collectsResult, registeredDateTime);

        Order savedOrder = orderRepository.save(order);
        return OrderCreateResponse.of(savedOrder);

//        final int[] totalPrice = {0};
//        targetProducts.forEach(product -> totalPrice[0] += product.getPrice());
//
//        orderRepository.save(
//                Order.builder()
//                        .totalPrice(totalPrice[0])
//                        .orderStatus(OrderStatus.INIT)
//                        .registeredDateTime(registeredDateTime)
//                        .build());
    }

    private List<Product> findOrderProductNumbers(List<String> orderNumbers) {
        List<Product> targetProducts = productRepository.findAllByProductNoIn(orderNumbers);

        Map<String, Product> collectedProductNumbers = targetProducts.stream()
                .collect(Collectors.toMap(Product::getProductNo, o -> o));

        return orderNumbers.stream()
                .map(collectedProductNumbers::get)
                .collect(Collectors.toList());
    }
}
