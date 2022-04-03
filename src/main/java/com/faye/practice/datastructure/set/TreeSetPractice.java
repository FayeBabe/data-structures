package com.faye.practice.datastructure.set;

import java.util.Set;
import java.util.TreeSet;

/*
 * A TreeSet uses a tree as underlying data structure. Storing elements and checking if an element is present both
 * happen in O(log n) time. TreeSets implement the "NavigableSet" interface, which lets you slice up a collection.
 *
 * Beware: NavigableSet is a subtype of SortedSet. So while HashSet implements Set, TreeSet implements SortedSet!
 */
public class TreeSetPractice {

    final Set<String> set = new TreeSet<>();

   TreeSetPractice() {

   }
}
