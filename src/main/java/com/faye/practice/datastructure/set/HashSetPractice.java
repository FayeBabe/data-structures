package com.faye.practice.datastructure.set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/*
 * HashSet is the most common type of set. Enforces uniqueness of elements and decides order of the elements based on
 * their hash code, which means the elements' hashCode() function is used.
 * Adding an element to a HashSet and checking if an element is in as HashSet both run in constant time.
 *
 * HashSet stores elements in buckets. Each bucket corresponds to a single hash code. A good hashing algorithm places
 * each hashed element in its own bucket.
 * To find out if an element already exist in a Set, which means it can't be added to the Set, Set.add(Object o)
 * compares the new object to all objects already in o's bucket with the equals method.
 *
 * Beware: because HashSet doesn't implement NavigableSet/SortedSet, when looping over a HashSet's elements, you will go
 * over them in arbitrary order. A TreeSet on the other hand implements NavigableSet, which extends SortedSet, and
 * therefore goes through the set's elements in sorted order.
 */
public class HashSetPractice {

    Set<Integer> set = new HashSet<>();

    /* The Set interface doesn't add any extra methods than what's in Collection. */
    {
        boolean isElementAdded = set.add(66); // add element to set. Returns true iff element was added
        boolean isSameElementAdded = set.add(66); // will be false
        set.add(10); set.add(8);
        set.forEach(elem -> System.out.printf("%d ", elem)); // prints elements of the set in arbitrary order

        /* Just to illustrate that contrary to a HashSet, a TreeSet's elements are printed in the correct order. */
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(66); treeSet.add(10); treeSet.add(8);
        for(Integer i : treeSet) {
            System.out.printf("%d ", i);
        }

    }
}
