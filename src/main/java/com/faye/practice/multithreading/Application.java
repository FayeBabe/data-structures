package com.faye.practice.multithreading;

import com.faye.practice.multithreading.model.SimpleTask;
import com.faye.practice.multithreading.model.SimpleThread;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        // Start a new Thread with a given task
        Thread t = new Thread(new SimpleTask(5)); t.start();

        // Show that there's no guarantee as to the order threads are run in.
        List<Runnable> allTasks = Arrays.asList(new SimpleTask(1), new SimpleTask(2), new SimpleTask(3));
        for(Runnable r : allTasks) new Thread(r).start();

        // Check the Daemon status of a thread:
        boolean isMyTheadADaemon = t.isDaemon();

        // Set daemon status of a very slow thread, for illustration purposes:
        Runnable r = new SimpleTask(99, true);
        Thread daemonThread = new Thread(r);
        daemonThread.setDaemon(true); // when true, thread ends well before number 99 is printed
        daemonThread.start();

        /*
         * Another way to create threads, is by defining a class that extends Thread and has a run() method.
         * You can then call .start() on an object of that class:
         */
        new SimpleThread().start();

        // When you want to wait for a thread to reach a specific state, you can free up CPU cycles using Thread.sleep()
        while(SimpleThread.counter < 100) {
            System.out.println("Waiting for counter to reach 100");
            Thread.sleep(1000); // can throw InterruptedException.
        }

        // Can you run multiple tasks with a single thread?

    }
}
