package com.example.practicaltest.spring.unit;

import com.example.practicaltest.spring.unit.beverages.Americano;
import com.example.practicaltest.spring.unit.beverages.Beverage;
import com.example.practicaltest.spring.unit.beverages.Latte;
import com.example.practicaltest.spring.unit.order.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CafeKioskTest {


    @Test
    void add_manual_test() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        System.out.println("담긴 수 : " + cafeKiosk.getBeverages().size());
        System.out.println("담긴 음료 : " + cafeKiosk.getBeverages().get(0).getName());
    }

    @Test
    void add_auto_test() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        // 같은 테스트임
        assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);

        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");

    }

    // happy case
    @Test
    void add_several_beverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano, 2);

        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages()).hasSize(2);

        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");

    }


    // exception case
    @Test
    void add_zero_beverage() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료는 1잔 이상부터 주문할 수 있습니다.")
        ;
    }


    @Test
    void remove() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);

        cafeKiosk.remove(americano);
        assertThat(cafeKiosk.getBeverages()).hasSize(0);
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @Test
    void clear() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        cafeKiosk.add(americano);
        cafeKiosk.add(latte);
        assertThat(cafeKiosk.getBeverages()).hasSize(2);

        cafeKiosk.clear();
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @Test
    void createOrder() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);
        Order order = cafeKiosk.createOrder();

        assertThat(order.getBeverageList()).hasSize(1);
        assertThat(order.getBeverageList().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    void createOrderWithCurrentTime() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);
        Order order = cafeKiosk.createOrder(LocalDateTime.of(2024,2,14,14,0));

        assertThat(order.getBeverageList()).hasSize(1);
        assertThat(order.getBeverageList().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    void createOrderWithWrongCurrentTime() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);
//        Order order = cafeKiosk.createOrder(LocalDateTime.of(2024,2,14,9,0));

        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2024, 2, 14, 9, 0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요.");
    }
}