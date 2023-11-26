package com.study.bookprac.part2;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.HashSet;

public class Item18 {

    @RequiredArgsConstructor
    public static class InstrumentHashSet<E> extends HashSet<E> {
        private int addCount = 0;

        public InstrumentHashSet(int initCap, float loadFactor) {
            super(initCap, loadFactor);
        }

        @Override
        public boolean add(E e) {
            addCount++;
            return super.add(e);
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            addCount += c.size();
            return super.addAll(c);
        }
    }

    public int getAddCount() {
        return getAddCount();
    }

}
