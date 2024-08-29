/*
https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.

Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.

Given the array points, return the minimum number of arrows that must be shot to burst all balloons.

 

Example 1:

Input: points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
Example 2:

Input: points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4
Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
*/
class Solution {
    /**
    Algo:
     Ans = num of overlaps + others = total - overlaps
      -we need that many arrows at minimum
      
    -trick - because these are coordinates. we need to think +ve and -ve and they cant overlap
    -corner - edges on int range -> a-b will overdlow in normal comparator
     */
    public int findMinArrowShots(int[][] points) {
        //Arrays.sort(points, (a,b) -> a[1] - b[1]);
        Arrays.sort(points, (a, b) -> {
            // We can't simply use the o1[1] - o2[1] trick, as this will cause an 
            // integer overflow for very large or small values.
            // also because we need to handle +ve and -ve values coordinates
            if (a[1] == b[1]) return 0;
            if (a[1] < b[1]) return -1;
            return 1;
        });

        int prevEnd = points[0][1];
        //count overlaps
        int overlaps = 0;
        for(int i=1;i<points.length;i++) {
            int[] curr = points[i];
            if(curr[0] <= prevEnd) {
                overlaps++;
                prevEnd = Math.min(prevEnd, curr[1]);//we take min end time, because we want to check if we can burst more balloons with SAME arrow
            } else {
                prevEnd = curr[1];
            }
        
        }
        return points.length - overlaps;
    }
    
    /*
    almost good

    public int findMinArrowShots2(int[][] points) {
        Arrays.sort(points, (a,b) -> a[0] - b[0]);
        int prevEnd = points[0][1];
        int overlaps = 0;
        for(int i=1;i<points.length;i++) {
            int[] curr = points[i];
            if(curr[0] <= prevEnd) {
                overlaps++;
                prevEnd = Math.min(prevEnd, curr[1]);
            } else {
                prevEnd = curr[1];
            }
        }
        return points.length - overlaps;
    }
    */
}
