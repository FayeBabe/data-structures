package com.faye.practice.multithreading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/*
 * Instead of running service.execute(Runnable), which is fire-and-forget (it's a void method), you can "submit" a
 * Runnable to get a Future object with the result.
 * You can check if the task is cancelled or completed, or cancel it.
 */
public class SubmitRunnablesPractice {

    public static void main(String[] args) {

        ExecutorService service = null;

        try {
            service = Executors.newSingleThreadExecutor();

            Future<?> result = service.submit(() -> {
                System.out.println("Print line 1");
            });

            try {
                // only after waiting a long time does it become clear if a thread is running, done or cancelled!
                Thread.sleep(1000);
                boolean futureIsDone = result.isDone();
                System.out.println("The task is " + (futureIsDone ? "" : "not ") + "done.");
                boolean futureIsCancelled = result.isCancelled();
                System.out.println("The task is " + (futureIsCancelled ? "" : "not ") + "cancelled.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            callableCollectionPractice();
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    // You can also give the ExecutorService a collection of Callables, which yields a list of Futures
    private static void callableCollectionPractice() {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();

            List<Callable<String>> callables = new ArrayList<>(); // no clue yet what Callable is

            /* You can invoke a collection of Callable objects, which returns a List of Future objects of the same type
             * as the Callables */
            List<Future<String>> outcomes = service.invokeAll(callables); // invoking a Collection of Callables could yield an InterruptedException!

            /* Invoking Callables with an ExecutorService is synchronous!!
             * Meaning, control is handed to the next line of code after all Callables have been completed. */

            // You can also invoke a Callable with a time limit:
            String outcome = service.invokeAny(Arrays.asList(() -> {return "str";}));

            /* You can also get the result of a submitted Runnable (so: get the value of the Future) either
             * synchronously without time limit, or synchronously while waiting at most n milliseconds: */
            Future<String> stringFuture = service.submit(() -> "str");
            String str1 = stringFuture.get(); // this locks the thread up as long as the Future isn't resolved yet
            try {
                String str2 = stringFuture.get(1000L, TimeUnit.MILLISECONDS); // this waits 1 second for the Future to resolve
                System.out.println("The contents of str2 are: " + str2);
            } catch (TimeoutException e) { // exception is thrown if the result is not resolved within 1000 milliseconds
                e.printStackTrace();
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }

    }
}
