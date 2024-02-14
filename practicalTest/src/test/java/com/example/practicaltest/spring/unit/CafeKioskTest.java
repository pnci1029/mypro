package com.example.practicaltest.spring.unit;

import com.example.practicaltest.spring.unit.beverages.Americano;
import com.example.practicaltest.spring.unit.beverages.Beverage;
import com.example.practicaltest.spring.unit.beverages.Latte;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}