package com.example.practicaltest.spring.spring.domain.order;

import com.example.practicaltest.spring.spring.domain.BaseEntity;
import com.example.practicaltest.spring.spring.domain.orderproduct.OrderProduct;
import com.example.practicaltest.spring.spring.domain.product.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private int totalPrice;
    private LocalDateTime registeredDateTime;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProduct;

    @Builder
    private Order(OrderStatus orderStatus, int totalPrice, LocalDateTime registeredDateTime, List<OrderProduct> orderProduct) {
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.registeredDateTime = registeredDateTime;
        this.orderProduct = orderProduct;
    }

    private Order(List<Product> productList, LocalDateTime registeredDateTime) {
        this.orderStatus = OrderStatus.INIT;
        this.totalPrice = calculateTotalPrice(productList);
        this.registeredDateTime = registeredDateTime;
        this.orderProduct = productList
                .stream().map(product -> new OrderProduct(this, product))
                .collect(Collectors.toList());
    }

    public static Order create(List<Product> productList, LocalDateTime registeredDateTime) {
        return new Order(productList, registeredDateTime);
    }

    private int calculateTotalPrice(List<Product> productList) {
        return productList.stream()
                .mapToInt(Product::getPrice)
                .sum();
    }
}
