package com.example.practicaltest.spring.unit;

import com.example.practicaltest.spring.unit.beverages.Americano;
import com.example.practicaltest.spring.unit.beverages.Latte;
import com.example.practicaltest.spring.unit.order.Order;

import java.time.LocalDateTime;

public class CafeKioskRunner {
    public static void main(String[] args) {

        CafeKiosk cafeKiosk = new CafeKiosk();

        cafeKiosk.add(new Americano());
        System.out.println(">>>아메리카노 추가");

        cafeKiosk.add(new Latte());
        System.out.println(">>>라떼 추가");

        int totalPrice = cafeKiosk.calculateTotalPrice();
        System.out.println(">>>전체 계산 금액 " + totalPrice);

        Order order = cafeKiosk.createOrder(LocalDateTime.now());
    }
}
