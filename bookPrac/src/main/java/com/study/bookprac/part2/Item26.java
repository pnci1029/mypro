package com.study.bookprac.part2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Item26 {
    // 잘못된 예시

    public static void main(String[] args) {
        Collection stamps = new ArrayList();
        stamps.add(new ArrayList<>());

        for (Iterator i = stamps.iterator(); i.hasNext(); ) {
            Object next = i.next();
        }
    }

}
