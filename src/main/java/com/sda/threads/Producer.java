package com.sda.threads;

public class Producer implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Queue.instanceOf().push("text");
        }
    }
}
