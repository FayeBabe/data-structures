package com.faye.practice.datastructure.queue;

import java.util.ArrayDeque;
import java.util.Queue;

/*
 * A Deque ("deck") is a double-ended queue, which means it stores elements which can then be retrieved in FIFO order,
 * but it lets you add and remove elements from both ends of the queue.
 *
 * It uses a resizable array as its internal data structure, and it is more efficient than an ArrayList, which
 * implements both List and Queue.
 */
public class ArrayDequePractice {

    Queue<String> deque = new ArrayDeque<>();
}
