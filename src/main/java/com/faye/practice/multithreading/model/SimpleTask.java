package com.faye.practice.multithreading.model;

public class SimpleTask implements Runnable {

    private final int i;
    private final boolean isVerySlow;

    public SimpleTask(int i) {
        this.i = i;
        isVerySlow = false;
    }

    public SimpleTask(int i, boolean isVerySlow) {
        this.i = i;
        this.isVerySlow = isVerySlow;
    }

    public void run() {
        if(isVerySlow) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");
                e.printStackTrace();
            }
        }
        System.out.println("Number: " + i);
    }
}
