package com.sda.threads;

public class MyTask implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("1");
            Thread.sleep(500);
            System.out.println("2");
            Thread.sleep(500);
            System.out.println("3");
            Thread.sleep(500);
            System.out.println("4");
            Thread.sleep(500);
            System.out.println("5");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
