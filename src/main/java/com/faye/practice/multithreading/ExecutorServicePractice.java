package com.faye.practice.multithreading;

import java.util.concurrent.*;

public class ExecutorServicePractice {

    public static void main(String[] args) {

        ExecutorService service = null;

        try {
            /*
             * Create new single-threaded executor service. It's a thread executor, meaning it runs threads.
             * Because it's a single thread executor, it runs each thread in order
             */

            service = Executors.newSingleThreadExecutor();

            System.out.println("Begin");
            service.execute(() -> {
                System.out.println("First thead made out of a Runnable based on lambda expression");
            });
            service.execute(() -> {
                for (int i = 0; i < 3; i++) {
                    System.out.println("Second thread. Printing number: " + i);
                }
            });
            service.execute(() -> {
                System.out.println("Third thread");
            });
            System.out.println("This line is printed after \"begin\".");

        // a "finally" block is needed to prevent memory leaks, because ExecutorService doesn't implement AutoCloseable.
        } finally {
            /*
             * The executor service runs its threads as non-daemon threads, which means the program won't stop until you
             * destroy the executor. The program does stop if you create the executor service (line 17) without letting
             * it run any threads.
             */
            if (service != null) {
                service.shutdown(); /* any tasks already submitted to the executor will finished, but it won't accept
                                     * new tasks. If you want to shut the thread executor service down immediately,
                                     * call shutdownNow();
                                     * shutdown() only works if threads can be finished. A static ExecutorService
                                     * instance would run as long as the program runs, so you'd need a static method to
                                     * shutdown the ExecutorService as well.
                                     */
            }
        }
    }
}
