package com.sda.threads;

public class Consumer implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Read value: " + Queue.instanceOf().pop());
        }
    }
}




