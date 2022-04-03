package com.faye.practice.algorithm.sorting.util;

import lombok.RequiredArgsConstructor;

import java.util.Comparator;

/*
 * This class shows the difference between Comparator and Comparable.
 *
 * Comparable is an interface with one method: compareTo. It returns a value less than 0, 0, or greater than zero,
 * depending on the comparison outcome between "this" and the single argument it gets.
 *
 * Comparator is also an interface with one method: compare. It similarly returns the above values, but receives two
 * objects as arguments.
 */
public class ComparatorPractice {

    /* In this case: order among multiple instances of CustomElement is decided based on their strings' lengths. */
    @RequiredArgsConstructor
    private static class CustomElement implements Comparable<CustomElement> {
        private String x;

        @Override
        public int compareTo(CustomElement o) {
            if (x.length() < o.x.length()) {
                return -1;
            } else if (x.length() == o.x.length()) {
                return 0;
            }
            return 1;
        }
    }

    private static class ElementNotImplementingComparable {
        private String x;
    }

    /*
     * The whole idea behind using Comparator over Comparable, is that you may want different ways of comparing two
     * objects. While the Comparable interface defines just a single way to compare two instances of a class, you could
     * define multiple classes that implement Comparator, each with their own criteria of how two class objects compare.
     * This one uses the first character of the objects' strings as comparison point. The next one uses the former
     * string length metric.
     */
    private static class ElementComparatorOnFirstChar implements Comparator<ElementNotImplementingComparable> {
        @Override
        public int compare(ElementNotImplementingComparable o1, ElementNotImplementingComparable o2) {
            if (o1.x.charAt(0) < o2.x.charAt(0)) {
                return -1;
            } else if (o1.x.charAt(0) == o2.x.charAt(0)) {
                return 0;
            }
            return 1;
        }
    }

    private static class ElementComparatorOnStringLength implements Comparator<ElementNotImplementingComparable> {
        @Override
        public int compare(ElementNotImplementingComparable o1, ElementNotImplementingComparable o2) {
            if (o1.x.length() < o2.x.length()) {
                return -1;
            } else if (o1.x.length() == o2.x.length()) {
                return 0;
            }
            return 1;
        }
    }
}
