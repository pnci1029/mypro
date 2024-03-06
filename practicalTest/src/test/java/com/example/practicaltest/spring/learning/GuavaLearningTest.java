package com.example.practicaltest.spring.learning;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GuavaLearningTest {

    @DisplayName("주어진 리스트를 원하는 크기만큼 분리한다.")
    @Test
    void partitionListTest() {
        // given
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);

        // when
        List<List<Integer>> partition = Lists.partition(integers, 3);

        // then
        assertThat(partition)
                .hasSize(2)
                .isEqualTo(List.of(
                        List.of(1, 2, 3),
                        List.of(4, 5, 6))
                );
    }

    @DisplayName("주어진 리스트를 원하는 크기만큼 분리한다.")
    @Test
    void partitionListTest2() {
        // given
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);

        // when
        List<List<Integer>> partition = Lists.partition(integers, 4);

        // then
        assertThat(partition)
                .hasSize(2)
                .isEqualTo(List.of(
                        List.of(1, 2, 3,4),
                        List.of(5, 6))
                );
    }

    @DisplayName("")
    @Test
    void multiMapTest() {
        // given
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("커피", "아메리카노");
        multimap.put("커피", "라떼");
        multimap.put("커피", "카푸치노");
        multimap.put("베이커리", "식빵");
        multimap.put("베이커리", "크루와상");

        // when
        Collection<String> multimapTarget = multimap.get("커피");

        // then
        assertThat(multimapTarget).hasSize(3)
                .isEqualTo(
                        List.of("아메리카노", "라떼", "카푸치노")
                );
    }

    @DisplayName("")
    @TestFactory
    Collection<DynamicTest> multiMapTest2() {
        // given
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("커피", "아메리카노");
        multimap.put("커피", "라떼");
        multimap.put("커피", "카푸치노");
        multimap.put("베이커리", "식빵");
        multimap.put("베이커리", "크루와상");

        return List.of(
                DynamicTest.dynamicTest("1개 value값 삭제", () ->{
                    //when
                    multimap.remove("커피", "카푸치노");

                    //then
                    Collection<String> result = multimap.get("커피");

                    assertThat(result).hasSize(2)
                            .isEqualTo(List.of("아메리카노", "라떼"));
                }),
                DynamicTest.dynamicTest("key 전체 삭제", () ->{
                    //when
                    multimap.removeAll("커피");
                    Collection<String> results = multimap.get("커피");

                    //then
                    assertThat(results).isEmpty();
                })
        );
    }
}
