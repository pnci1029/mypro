package com.example.practicaltest.spring.spring.domain.stock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@SpringBootTest
class StockTest {

    @Autowired
    StockRepository stockRepository;

    @DisplayName("요청 상품 갯수보다 재고가 적을경우 false 아니면 true를 반환한다.")
    @Test
    void isDecreasable() {
        // given
        Stock stock1 = Stock.create("001", 1);
        Stock stock2 = Stock.create("002", 5);

        stockRepository.saveAll(List.of(stock1, stock2));
        // when
        boolean stock1Result = stock1.isDecreasable(3);
        boolean stock2Result = stock2.isDecreasable(3);

        // then
        assertThat(stock1Result).isTrue();
        assertThat(stock2Result).isFalse();
    }


    @DisplayName("재고 상품의 수량을 원하는만큼 감소시킨다.")
    @Test
    void decrease() {
        // given
        Stock stock1 = Stock.create("001", 2);
        Stock stock2 = Stock.create("002", 5);

        stockRepository.saveAll(List.of(stock1, stock2));

        // when
        stock1.decrease(1);
        stock2.decrease(2);

        stockRepository.saveAll(List.of(stock1, stock2));

        List<Stock> stockList = stockRepository.findAllByProductNoIn(List.of("001", "002"));
        Map<String, Stock> stockMap = stockList.stream()
                .collect(Collectors.toMap(Stock::getProductNo, stock -> stock));
        // then
        assertThat(stockMap.get(stock1.getProductNo()).getQuantity()).isEqualTo(1);
        assertThat(stockMap.get(stock2.getProductNo()).getQuantity()).isEqualTo(3);

        assertThat(stock1.getQuantity()).isEqualTo(1);
    }


    @DisplayName("재고보다 많은 양을 주문하려고 하면 예외가 발생한다.")
    @Test
    void decreaseOverStock() {
        // given
        Stock stock = Stock.create("001", 2);
        int quantity = 3;

        // when // then
//        stock.decrease(quantity);

        assertThatThrownBy(() -> {
            stock.decrease(quantity);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("재고가 부족합니다. 관리자에게 문의하세요.");
    }

}