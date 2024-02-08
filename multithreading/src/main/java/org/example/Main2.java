package org.example;

public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread.getName() = " + Thread.currentThread().getName());
            }
        });
        thread.setName("new thread worker1");

        System.out.println("before thread : "+thread.getContextClassLoader().getName());
        thread.start();
        System.out.println("starting thread : "+thread.getContextClassLoader().getName());

        Thread.sleep(1000);


    }
}
