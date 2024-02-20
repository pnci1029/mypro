package com.example.practicaltest.spring.spring.domain.stock;

import com.example.practicaltest.spring.spring.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productNo;
    private int quantity;

    @Builder
    private Stock(String productNo, int quantity) {
        this.productNo = productNo;
        this.quantity = quantity;
    }

    public static Stock create(String productNo, int quantity) {
        return Stock.builder()
                .productNo(productNo)
                .quantity(quantity)
                .build();
    }
}
