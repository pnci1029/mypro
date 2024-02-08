package org.example;

public class Main3 {
    public static void main(String[] args) {
        NewThread thread = new NewThread();

        thread.start();
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            System.out.println("thread.getName() = " + Thread.currentThread().getName());
        }
    }

}
