package com.faye.practice.datastructure.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Most frequently used type of list. It's ordered, elements can be retrieve in constant time, while adding and removing
 * elements is slower. That makes it good for situations where you read data more than you write. Reading, adding and
 * removing from beginning and end of an ArrayList happens in constant time, which makes ArrayList good to use as a
 * queue. ArrayList is a faster, thread-unsafe alternative to Vector, which is now longer used.
 */
public class ArrayListPractice {

    /* First, some general Collection functions */
    final List<String> list = new ArrayList<>();

    {
        boolean successfulAdd = list.add("test"); // Collection.add(Object) returns true iff element could be added to the collection.
        boolean successful2ndAdd = list.add("test"); // also true, because lists don't require element uniqueness.

        boolean successfulRemove = list.remove("test"); // Collection.remove(Object) removes a SINGLE matching value from the list.
        System.out.println("Number of elements in list: " + list.size());

        String removedElement = list.remove(0); // Collection.remove(int) removes an element by index and returns its value, instead of whether the removal was successful
        System.out.println("Last removed element: " + removedElement);

        try {
            String unsuccessfullyRemovedElem = list.remove(0);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage()); // Removing a nonexisting element by index yields an IndexOutOfBoundsException
        }

        boolean isListEmpty = list.isEmpty(); // Collection.isEmpty()
        int listSize = list.size(); // Collection.size()

        list.clear(); // Collection.clear()
        boolean containsElement = list.contains("test"); // Collection.contains(Object)

        /*
         * There's no built-in method to get a Collection's capacity,
         * and neither is there one in List.
         * However, starting capacity is 10, and it doubles whenever
         * the elements outgrow the Collection capacity.
         */

        listMethods();
        listInitialization();
        listIteration();
    }

    /*
     * All the examples below would also work with LinkedList, Vector and Stack, but they'd be less efficient.
     * Only noticeable with larger lists.
     */
    private void listMethods() {
        List<String> list = new ArrayList<>();

        // You can add elements to end of list, or at a specific location
        list.add("string1"); // list: ["string1"]
        list.add("string1"); // list: ["string1", "string1"]
        list.add(0, "string0"); // list: ["string0", "string1", "string1"]

        // You can remove the first occurrence of an element, or remove the element at an index i
        list.remove("string1"); // list: ["string0", "string1"]
        list.remove(0); // list: ["string1"]

        // (print list contents)
        System.out.print("list: ");
        printList(list);

        // Find the index of the first and last occurrence of an element:
        list.add("string1"); // list: ["string1", "string1"]
        int indexOfFirstOccurrence = list.indexOf("string1");
        int indexOfLastOccurrence = list.lastIndexOf("string1");

        // Remove element from List:
        list.remove("string1"); // removes first occurrence. list: ["string1"]
        list.remove(0); // remove element at index 0. list: []

        // Set element at given index and return original element at that index if there was one:
        list.add("dummy"); // add dummy element. list: ["dummy"]
        String oldElement = list.set(0, "test"); // replace with new element. list: ["test"]. Returns "dummy"

        // You can't "set" the element at an index that doesn't exist yet, yields IndexOutOfBoundsException
        try {
            list.set(1, "impossible");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Cannot replace element #1 of List, when list contains only 1 element");
        }
    }

    /*
     * Some convenient ways to initialize lists with multiple values.
     */
    private void listInitialization() {
        /*
         * Arrays.asList takes an Object[] or ...Object as parameter
         * Beware: the result is not an ArrayList, but a plain List with an internal array of a fixed size!
         */
        List<String> list = Arrays.asList("1", "2", "3", "4");
        List<String> list2 = Arrays.asList(new String[]{"1", "2", "3", "4"});

        try {
            list2.add("5");
        } catch (UnsupportedOperationException ex) {
            System.out.println("Cannot add element to List with array of fixed size!");
        }

        /*
         * Initialize List from stream.
         * WARNING: Collectors.toList() doesn't guarantee which List implementation is used! It also doesn't guarantee
         * mutability, serializability or thread safety of the returned List.
         */
        List<String> list3 = Stream.of("1", "2").collect(Collectors.toList());

        /* Initialize List with factory method (Java 9) */
        List<String> list4 = List.of("1", "2");

        /*
         * Initialize List with double-brace notation.
         * This declares an anonymous inner class that extends ArrayList. The details of the subclass are in the double
         * braces. The inner pair of braces is actually an initializer block. DON'T DO THIS, it's an antipattern.
         */
        List<String> list5 = new ArrayList<>() {{
           add("1");
           add("2");
        }};
    }

    private void listIteration() {
        List<String> list = Arrays.asList("1", "2", "3", "4");

    }

    /*
     * There's no convenient way to get the last element of a List l, besides using:
     * l.get(l.size() - 1)
     */
    private static void printList(final List list) {
        System.out.print('[');
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.printf("%s, ", list.get(i));
        }
        System.out.printf("%s]\n", list.get(list.size() - 1));
    }

}
