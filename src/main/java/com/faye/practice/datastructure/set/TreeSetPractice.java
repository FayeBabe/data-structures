package com.faye.practice.datastructure.set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/*
 * A TreeSet uses a tree as underlying data structure. Storing elements and checking if an element is present both
 * happen in O(log n) time. TreeSets implement the "NavigableSet" interface, which lets you slice up a collection.
 *
 * Beware: NavigableSet is a subtype of SortedSet. So while HashSet implements Set, TreeSet implements SortedSet!
 */
public class TreeSetPractice {

    /* Example of a TreeSet of custom objects that can be compared with a Comparator, to make it interesting. */
    final TreeSet<CustomComponent> set = new TreeSet<>();

    {
        /*
         * TreeSet works mostly like any other Set, but because it implements NavigableSet instead of a plain Set, it has
         * a notion of the order of its elements. For a given element x, you can search for:
         * - the greatest element < x with set.lower(x)
         * - the greatest element <= x with set.floor(x)
         * - the smallest element >= x with set.ceiling(x)
         * - the smallest element > x with set.higher(x)
         * lower() and higher(), respectively, and it can find the first and last elements with floor() and ceiling().
         */
        Arrays.asList("1", "2", "3").stream()
                .forEach(elem -> set.add(new CustomComponent(elem)));

        CustomComponent lt = set.lower(set.last()); // "2"; using set.last() as a learning aid
        CustomComponent leq = set.floor(set.last()); // "3"
        CustomComponent geq = set.ceiling(set.last()); // "3"
        CustomComponent gt = set.higher(set.last()); // null, because this element doesn't exist

        System.out.println("\nlt: " + lt);
        System.out.println("leq: " + leq);
        System.out.println("geq: " + geq);
        System.out.println("gt: " + gt);
    }


    @RequiredArgsConstructor
    @Getter
    static class CustomComponent implements Comparable<CustomComponent> {

        private final String str;

        @Override
        public int compareTo(CustomComponent o2) {
            if (str.charAt(0) < o2.str.charAt(0)) {
                return -1;
            } else if (str.charAt(0) == o2.str.charAt(0)) {
                return 0;
            }
            return 1;
        }

        @Override
        public String toString() {
            return str;
        }
    }

}
