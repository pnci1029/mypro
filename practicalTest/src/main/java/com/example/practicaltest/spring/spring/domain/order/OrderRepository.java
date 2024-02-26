package com.example.practicaltest.spring.spring.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o " +
            "from Order o " +
            "where o.registeredDateTime >= :startedDate " +
            "and o.registeredDateTime < :endDate " +
            "and o.orderStatus = :orderStatus")
    List<Order> findOrdersBy(LocalDateTime startedDate, LocalDateTime endDate, OrderStatus orderStatus);
}
