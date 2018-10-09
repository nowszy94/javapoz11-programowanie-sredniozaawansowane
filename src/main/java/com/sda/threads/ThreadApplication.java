package com.sda.threads;

public class ThreadApplication {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    Queue.instanceOf().increment();
                }
            }).start();
        }

        Thread.sleep(100);
        System.out.println(Queue.instanceOf().getValue());
    }
}
