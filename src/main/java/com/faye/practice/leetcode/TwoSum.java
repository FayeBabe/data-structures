package com.faye.practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 */
public class TwoSum {

    // maps each given element to its list of indices that contain the element
    Map<Integer, List<Integer>> map = new HashMap<>();


    public int[] twoSum(int[] nums, int target) {
        // pass through nums once to create indices of each element. O(n)
        for(int i = 0; i < nums.length; i++) {
            addToMap(nums[i], i);
        }

        // pass through nums again, for each num, check if complement is in hashMap
        for(int i = 0; i < nums.length; i++) {
            int number = nums[i];
            int complement = target - number;
            int minNrOfComplements = number == complement ? 2 : 1;

            if(map.keySet().contains(complement) && map.get(complement).size() >= minNrOfComplements) {
                int complementIndex = map.get(complement).get(map.get(complement).size() - 1); //always get last element in complement number's bucket
                return new int[]{i, complementIndex};
            }
        }
        return new int[]{99, 99};
    }

    private void addToMap(int num, int index) {
        // System.out.println("num: " + num + ", index: " + index);
        if (!map.keySet().contains(num)) {
            map.put(num, new ArrayList<Integer>());
        }
        map.get(num).add(index);
    }

}

//class OldSolution {
//
//    // O(n^2) time complexity
//     public int[] twoSum(int[] nums, int target) {
//         for(int i = 0; i < nums.length; i++) {
//             int j = nums.length - 1;
//             while(j > i) {
//                 if(nums[i] + nums[j] == target) {
//                     return new int[]{i, j};
//                 }
//             }
//         }
//         return new int[]{0,0};
//     }
//
//    // O(log n) time complexity by sorting first
//    public int[] twoSum(int[] nums, int target) {
//        List<Integer> numsList = Arrays.stream(nums).boxed().collect(Collectors.toList()); // O(n)
//        List<Integer> sortedNums = sort(numsList, target); //O(log n)
//
//        for (int i = 0; i < sortedNums.size(); i++) {
//            int j = sortedNums.size() - 1;
//            while (j > i) {
//                if (sortedNums.get(i) + sortedNums.get(j) == target) {
//                    return new int[]{i, j};
//                }
//            }
//        }
//        return null;
//    }
//
//    // merge sort, O(log n)
//    private List<Integer> sort(final List<Integer> nums, int target) {
//        if (nums.size() < 2) {
//            return nums;
//        }
//
//        List<Integer> left = sort(nums.subList(0, nums.size() / 2 - 1), target);
//        List<Integer> right = sort(nums.subList(nums.size() / 2, nums.size() - 1), target);
//
//        return merge(left, right, target);
//    }
//
//    private List<Integer> merge(final List<Integer> left, final List<Integer> right, int target) {
//        if (left.size() == 0) {
//            return right;
//        } else if (right.size() == 0) {
//            return left;
//        }
//
//        int totalMergedListSize = left.size() + right.size();
//        List<Integer> mergedList = new ArrayList<Integer>();
//        for (int i = 0; i < totalMergedListSize; i++) {
//            if (left.get(0) > target) {
//                left.remove(left.get(0));
//            }
//            if (right.get(0) > target) {
//                right.remove(right.get(0));
//            }
//            if (left.get(0) > right.get(0)) {
//                mergedList.add(right.get(0));
//                left.remove(right.get(0));
//            } else {
//                mergedList.add(left.get(0));
//                left.remove(left.get(0));
//            }
//        }
//        return mergedList;
//    }
//}
