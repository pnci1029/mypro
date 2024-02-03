package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final Lock lock = new ReentrantLock();
    private static int counter = 0;

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<Integer> box = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

//        int counter = 0;

        box.parallelStream().forEach(value -> {
            counter += value + 1;
            System.out.println(counter);
        });






        System.out.println("Part 1");
        System.out.println("────────────────────────────────────────────");
        long startTime1 = System.currentTimeMillis();

        // 순차 처리
        for (Integer value : box) {
            String threadName = Thread.currentThread().getName();
            LocalDateTime currentTime = LocalDateTime.now();
            System.out.printf(currentTime.format(formatter) + " -> Thread Name: %s, Stream Value: %s\n", threadName, value);
        }

        long endTime1 = System.currentTimeMillis();
        System.out.println("────────────────────────────────────────────");
        System.out.println("Elapsed Time (Part 1): " + (endTime1 - startTime1) + " milliseconds");
        System.out.println();

        System.out.println("Part 2");
        System.out.println("────────────────────────────────────────────");
        long startTime2 = System.currentTimeMillis();

        // 병렬 처리
        box.parallelStream().forEach(value -> {
            String threadName = Thread.currentThread().getName();
            LocalDateTime currentTime = LocalDateTime.now();
            System.out.printf(currentTime.format(formatter) + " -> Thread Name: %s, Stream Value: %s\n", threadName, value);

        // 시간 확인을 위해 2초간 sleep 함
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long endTime2 = System.currentTimeMillis();
        System.out.println("────────────────────────────────────────────");
        System.out.println("Elapsed Time (Part 2): " + (endTime2 - startTime2) + " milliseconds");
    }
}
