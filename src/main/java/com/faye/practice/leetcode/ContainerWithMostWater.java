package com.faye.practice.leetcode;

/*
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 * Notice that you may not slant the container.
 */
public class ContainerWithMostWater {

    /*
     * After realizing I don't need to stop searching for a solution after a local optimum (so in other words, don't use
     * a  hill climber algorithm that gets stuck on local optima).
     *
     * LEARNING: when I get the error "output area exceeded", it means my print statements take up too much memory!
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        // maximum area encountered so far
        int area = (right - left) * Math.min(height[left], height[right]);

        while(right - left > 1) {
            if(height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }

            // Keep area if it has increased.
            if ((right - left) * Math.min(height[left], height[right]) > area) {
                area = (right - left) * Math.min(height[left], height[right]);
            }
        }

        return area;
    }

    // This hits a local optimum on input [2,3,4,5,18,17,6]:
//    public int maxArea(int[] height) {
//        int left = 0;
//        int right = height.length - 1;
//        int area = (right - left) * Math.min(height[left], height[right]);
//
//        boolean aBarIsMoved = false;
//        do {
//            aBarIsMoved = false;
//
//            // if left is lower
//            if(height[left] < height[right]) {
//                System.out.println("left bar is lower");
//                int newLeft = left;
//                // keep moving it right until a longer bar is found
//                while(newLeft < right - 1) {
//                    newLeft++;
//                    if(height[newLeft] > height[left]) {
//                        break;
//                    }
//                }
//                System.out.println("New left is now at: " + newLeft);
//                // You've found the first left-hand bar that's longer than the original left-hand bar.
//                // Keep it if the area has increased.
//                if ((right - newLeft) * Math.min(height[newLeft], height[right]) > area) {
//                    left = newLeft;
//                    area = (right - newLeft) * Math.min(height[newLeft], height[right]);
//                    aBarIsMoved = true;
//                    System.out.println("Left bar moved. Area is now: " + area);
//                }
//
//                // if right is lower
//            } else if(height[right] < height[left]) {
//                System.out.println("right bar is lower");
//                int newRight = right;
//                // keep moving it right until a longer bar is found
//                while(newRight > left + 1) {
//                    newRight--;
//                    if(height[newRight] > height[right]) {
//                        break;
//                    }
//                }
//                System.out.println("New right is now at: " + newRight);
//                // You've found the first left-hand bar that's longer than the original left-hand bar.
//                // Keep it if the area has increased.
//                if ((newRight - left) * Math.min(height[left], height[newRight]) > area) {
//                    right = newRight;
//                    area = (newRight - left) * Math.min(height[left], height[newRight]);
//                    aBarIsMoved = true;
//                    System.out.println("Right bar moved. Area is now: " + area);
//                }
//
//                // if both bars have same height, they must both move as long as area increases
//            } else {
//                System.out.println("both bars equally high");
//                int newLeft = left;
//                int newRight = right;
//
//                // keep moving it right until a longer bar is found
//                while(newLeft < right - 1) {
//                    newLeft++;
//                    if(height[newLeft] > height[left]) {
//                        break;
//                    }
//                }
//                while(newRight > left + 1) {
//                    newRight--;
//                    if(height[newRight] > height[right]) {
//                        break;
//                    }
//                }
//
//                if ((newRight - newLeft) * Math.min(height[newLeft], height[newRight]) > area) {
//                    left = newLeft;
//                    right = newRight;
//                    area = (newRight - newLeft) * Math.min(height[newLeft], height[newRight]);
//                    aBarIsMoved = true;
//                    System.out.println("Both bars moved. Area is now: " + area);
//                }
//            }
//        } while(aBarIsMoved);
//
//        return area;
//    }

    // Runs in O(n^2)
//    public int maxArea(int[] height) {
//        int area = -1;
//
//        for (int leftLineX = 0; leftLineX < height.length - 1; leftLineX++) {
//            for(int rightLineX = leftLineX + 1; rightLineX < height.length; rightLineX++) {
//                int width = rightLineX - leftLineX;
//                int currentHeight = Math.min(height[leftLineX], height[rightLineX]);
//
//                if (width * currentHeight > area) {
//                    area = width * currentHeight;
//                }
//            }
//        }
//        return area;
//    }
}
