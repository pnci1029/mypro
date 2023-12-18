package com.study.bookprac.part2;

import java.util.HashSet;
import java.util.Set;

public class Item30 {
    //before
    public static Set Union1(Set item1, Set item2) {
        Set result = new HashSet(item1);
        result.add(item2);
        return result;
    }

    //after
    public static <E>Set <E> Union2(Set<E> item1, Set<E> item2) {
        Set<E> set = new HashSet<>(item1);
        set.addAll(item2);
        return set;
    }

    public static <E>Set <E> Union3(Set<E> item1, Set<E> item2) {
        Set<String> result1 = Set.of("aa", "bb", "cc");
        Set<String> result2 = Set.of("11", "22", "33");
        Set<String> result = Union2(result1, result2);
        return (Set<E>) result;
    }
}
