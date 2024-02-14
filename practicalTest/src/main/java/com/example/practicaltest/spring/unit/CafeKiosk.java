package com.example.practicaltest.spring.unit;

import com.example.practicaltest.spring.unit.beverages.Beverage;
import com.example.practicaltest.spring.unit.order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CafeKiosk {

    private final List<Beverage> beverages = new ArrayList<>();

    public void add(Beverage beverage) {
        beverages.add(beverage);
    }

    public void remove(Beverage beverage) {
        beverages.remove(beverage);
    }

    public void clear() {
        beverages.clear();
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (Beverage beverage : beverages) {
            totalPrice += beverage.getPrice();
        }

        return totalPrice;
    }

    public Order createOrder() {
        return new Order(LocalDateTime.now(), beverages);
    }
}
