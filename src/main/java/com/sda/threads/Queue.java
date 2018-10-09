package com.sda.threads;

import java.util.LinkedList;

public class Queue {

    private static Queue instance;

    public synchronized static Queue instanceOf() {
        if (instance == null) {
            instance = new Queue();
        }
        return instance;
    }

    private Queue() {
    }

    private LinkedList<String> items = new LinkedList<>();

    private int value =0;

    public synchronized void increment() {
        value++;
    }

    public int getValue() {
        return value;
    }

    public void push(String message) {
        items.push(message);
    }

    public String pop() {
        return items.pop();
    }
}
