package com.faye.practice.datastructure.queue;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;

/*
 * A Deque ("deck") is a double-ended queue, which means it stores elements which can then be retrieved in FIFO order,
 * but it lets you add and remove elements from both ends of the queue.
 *
 * An ArrayDeque (array-deque) is a deque that uses an array as its internal data structure.
 * It's more efficient than an ArrayList, which implements both List and Queue.
 *
 * When we reason about queues, we say that the back of the queue is the queue's rear end. That's where items are added.
 * The "front" of the queue is like the front of the line, which is where items can be retrieved. Elements at the front
 * of the queue are like customers at the front of the line: they're the first to leave the line.
 *
 * At the back ("tail") of a queue, elements are added with add(Object) or offer(Object).
 * At the back of a queue, you can get the next element with element()
 *
 * At the front ("head") of the queue, elements are added with push(Object).
 * You can see which element is at the front end of the queue with peek() without changing the queue's contents.
 * You can remove elements from the front of a queue with 2 methods: poll() gets the element or null if the queue is
 * empty, while pop() and remove() gets the element or throws an exception(!) if the queue is empty.
 *
 *
 *  push ......................................... head - o - o - o - tail .. add
 *  offerFirst ...................................                         .. offer / offerLast
 *  poll (gets and removes head) .................
 *  element (gets and removes head, ..............
 *      throws exception if there is none) .......
 *  pop (same as element(), only implemented .....
 *      in Stack, not in Queue) ..................
 *  peek (gets head without removing) ............
 *  pollFirst ....................................                        ..  pollLast
 *
 * FYI: ArrayDeque and LinkedList work exactly the same way!
 */
public class ArrayDequePractice {

    ArrayDeque<String> deque = new ArrayDeque<>();
    private int counter = 0;

    {
        boolean add0IsSuccessful = deque.add(getCounter()); // deque: (front) "0" (back);           add 0 to back
        boolean add1IsSuccessful = deque.add(getCounter()); // deque: (front) "0", "1" (back);      add 1 to back
        boolean add2IsSuccessful = deque.add(getCounter()); // deque: (front) "0", "1", "2" (back); add 2 to back
        System.out.println("Contents of deque: " + deque.toString());

        /* Note: offering an element only fails if the element is null! Otherwise, it returns true. */
        boolean offer3IsSuccessfulDeque = deque.offer(getCounter());
        System.out.println("Contents of deque: " + deque.toString()); // deque: (front) "0", "1", "2", "3" (back); add 3 to back

        try {
            boolean offer4IsUnsuccessfulDeque = deque.offer(null);
        } catch (NullPointerException ex) {
            System.out.println("Offering null value yields NullPointerException");
        }

        /* You can offer an element to the front or the back of a queue, like so: */
        boolean offerBack = deque.offerLast(getCounter()); // Same as a regular offer()! Adds 4
        boolean offerFront = deque.offerFirst(getCounter()); // Adds 5 to FRONT of the queue.
        System.out.println("Contents of deque: " + deque.toString()); // deque: (front) "5", "0", "1", "2", "3", "4" (back)

        /* To keep deque small, I'll remove some elements at the front (head) of the queue with remove() */
        String removedElement = deque.remove();
        System.out.println("Contents of deque: " + deque.toString()); // removed "5"
        String anotherRemovedElement = deque.pop();
        System.out.println("Contents of deque: " + deque.toString()); // removed "0"

        /* As alternative to offerFirst, you can also push element to front (head) of the queue with push(). */
        deque.push("x");

        /* verify that x is at the head of the queue, with peek. this won't remove x */
        String headOfDeque = deque.peek();
        System.out.println("Is head of queue still x? " + deque.peek().equals("x"));

        /* let's clear the queue, to illustrate poll() which removes and returns the head of queue, or null if queue is empty */
        for (int i = 0; i < 5; i++) {
            deque.poll();
        }
        String nonexistingHeadOfQueue = deque.poll();
        System.out.println("Verify that poll retrieves null because queue is empty: " + nonexistingHeadOfQueue);

        /*
         * Note that you can poll the front or back of the queue.
         * Just as with offer(E), which also comes in flavors offerFirst(E) and the default offerLast(E),
         * polling likewise comes in flavors pollFirst() and the default pollLast();
         */
        String x = deque.pollLast();
        String y = deque.pollFirst();

        /* Find out if you can get element at tail of the queue without removing it */
        deque.add("x");
        deque.add("y");
        deque.add("z");
        System.out.println("Contents of deque: " + deque.toString());
        String tailEndOfQueue = deque.element();
        System.out.println("Contents of tail are: " + tailEndOfQueue);

        stackUsingArrayDeque();
    }

    /*
     * You can create a stack using an ArrayDeque, by letting it maintain LIFO (last in, first out).
     * Stacks use push, pop and peek. There's no
     */
    private void stackUsingArrayDeque() {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        int topOfStack = stack.peek(); // 3

        int stillTopOfStack  = stack.pop(); // 3
        int newTopOfStack = stack.pop(); // 2
        int lastTopOfStack = stack.pop(); // 1

        try {
            int topOfEmptyStack = stack.pop();
        } catch (NoSuchElementException ex) {
            System.out.println("Trying to pop an empty stack yields NoSuchElementException!");
        }

    }

    private String getCounter() {
        return String.valueOf(counter++);
    }
}
