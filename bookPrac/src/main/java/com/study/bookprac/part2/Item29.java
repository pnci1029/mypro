package com.study.bookprac.part2;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Item29 {

    // before
    public static class Stack {
        private Object[] elements;
        private int size = 0;
        private static final int DefaultInitialCapacity = 16;

        public Stack(){
            elements = new Object[DefaultInitialCapacity];
        }

        public void push(Object e) {
            ensureCapacity();
            elements[size++] = e;
        }

        public Object pop() {
            if (size == 0) {
                throw new EmptyStackException();
            }
            Object result = elements[--size];
            return result;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private void ensureCapacity() {
            if (elements.length == size) {
                elements = Arrays.copyOf(elements, 2 * size + 1);
            }
        }

    }

    // after
    public static class Stack2<E>{
        private E[] elements;
        private int size = 0;
        private static final int DefaultInitialCapacity = 16;

        public Stack2(){
            elements =(E[]) new Object[DefaultInitialCapacity];
        }

        public void push(E e) {
            ensureCapacity();
            elements[size++] = e;
        }

        public E pop() {
            if (size == 0) {
                throw new EmptyStackException();
            }
            E result = elements[--size];
            return result;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private void ensureCapacity() {
            if (elements.length == size) {
                elements = Arrays.copyOf(elements, 2 * size + 1);
            }
        }
    }

}
