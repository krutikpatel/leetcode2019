/*
986. Interval List Intersections
DescriptionHintsSubmissionsDiscussSolution

Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

 

Example 1:

Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
*/
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
              
        List<int[]> ret = new ArrayList<>();
        int i=0;
        int j=0;
        while(i<A.length && j < B.length) {
            //check for intersection. Intersection is : startMax to EndMin
            int startMax = Math.max(A[i][0] , B[j][0]);
            int endMin = Math.min(A[i][1] , B[j][1]);
            
            if(endMin>=startMax) {
                ret.add(new int[]{startMax,endMin});
            }
            
            //which one to move? the one which ends first, because other one might still intersect with next
            if(A[i][1] == endMin) {
                i++;
            } 
            if(B[j][1] == endMin) {
                j++;
            }
            
        }
        
        int[][] retarr = new int[ret.size()][2];
        for( i=0;i<retarr.length;i++){
            retarr[i] = ret.get(i);
        }
            
        return retarr;
    }
}
