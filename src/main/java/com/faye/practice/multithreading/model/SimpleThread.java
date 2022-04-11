package com.faye.practice.multithreading.model;

public class SimpleThread extends Thread {

    public static int counter = 0;

    public void run() {
        System.out.println("Running a custom instance of a Thread!");
        for(int i = 0; i < 500; i++) {
            SimpleThread.counter++;
        }
    }


}
