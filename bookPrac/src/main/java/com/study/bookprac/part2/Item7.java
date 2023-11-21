package com.study.bookprac.part2;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Item7 {

    public class Stack{
        private Object[] elements;

        private int size = 0;
        private static final int Default_Initial_Capacity = 16;

        public Stack() {
            elements = new Object[Default_Initial_Capacity];
        }

        public void push(Object object) {
            ensureCapacity();
            elements[size++] = object;
        }

        public Object pop() {
            if (size == 0) {
                throw new EmptyStackException();
            }
            return elements[--size];
        }


        private void ensureCapacity() {
            if (elements.length == 0) {
                elements = Arrays.copyOf(elements, 2 * size + 1);
            }
        }
    }
}
