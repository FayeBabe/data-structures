package com.faye.practice.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ServiceTerminationPractice {

    public static void main(String[] args) {

        ExecutorService service = null;

        try {
            service = Executors.newSingleThreadExecutor();

            // submit a few tasks that each take a while to complete, so the ExecutorService needs a while to finish
            for (int i = 0; i < 5; i++) {
                service.submit(() -> {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Task has finished");
                });
            }
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }

        if (service != null) {
            try {
                System.out.println("Is service shut down? (yes!) " + service.isShutdown());
                System.out.println("Is service terminated? (no) " + service.isTerminated());

                service.awaitTermination(1, TimeUnit.MINUTES); // wait synchronously for a minute for the ExecutorService to finish

                if (service.isTerminated()) {
                    System.out.println("The service is now not only shut down, but also terminated.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace(); // if not all tasks run by the ExecutorService finish in time, an InterruptedException is thrown
            }
        }
    }
}