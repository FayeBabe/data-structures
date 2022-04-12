package com.faye.practice.multithreading;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/*
 * You can schedule tasks to be run on an exact schedule (once every n timeunits), or on a delay (n timeunits after the
 * previous task finished).
 */
public class ServiceSchedulingPractice {

    public static void main(String[] args) {
        System.out.println("Start main() function");
        ScheduledExecutorService service = null;

        try {
            service = Executors.newSingleThreadScheduledExecutor();
            List<Callable<String>> tasks = Arrays.asList(() -> {
                Thread.sleep(1000);
                return "Callable 1";
            }, () -> {
                Thread.sleep(1000);
                return "Callable 2";
            }, () -> {
                Thread.sleep(1000);
                return "Callable 3";
            }, () -> {
                Thread.sleep(1000);
                return "Callable 4";
            });
            Runnable task1 = () -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Runnable 1 is finished");
            };

            // Run a task (Runnable) after a delay;
            service.schedule(task1, 2000L, TimeUnit.MILLISECONDS);

            // Run a task (Callable) after a delay:
            ScheduledFuture<String> s = service.schedule(tasks.get(0), 2000L, TimeUnit.MILLISECONDS);
            try {
                System.out.println("Outcome of scheduled Runnable task: " + s.get(5000L, TimeUnit.MILLISECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

            Runnable task2 = () -> System.out.println("Runnable 2");
            Runnable task3 = () -> System.out.println("Runnable 3");

            /*
             * Run a task (Runnable) repeatedly every n TimeUnits AS A DEAMON THREAD!
             * Apparently, I must also run a non-daemon thread to ensure task2 is run repeatedly.
             */
            service.scheduleWithFixedDelay(task2, 2000L, 500L, TimeUnit.MILLISECONDS);
            service.scheduleAtFixedRate(task3, 3000L, 750L, TimeUnit.MILLISECONDS);

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

}
