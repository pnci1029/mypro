package com.example.practicaltest.spring.unit.beverages;

public class Latte implements Beverage{
    @Override
    public int getPrice() {
        return 3500;
    }

    @Override
    public String getName() {
        return "라떼";
    }
}
