/*
https://leetcode.com/problems/non-overlapping-intervals/description/

Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

 

Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 
*/
class Solution {
    /**
     rephrase problem - 
    "Given a collection of intervals, find the maximum number of intervals that are non-overlapping."
    -> if u remove a larger interval that overlaps, remaining ones will be max non - overlapping


    
    -sort by start time. if start time same, sort by end time (keep smaller end time first)
    -then compare end with next start
    -no need to store we just need count

    Note: sorting by end time also works, little diff logic though
     */
     /*
    public int eraseOverlapIntervals(int[][] intervals) {
        //Arrays.sort(intervals, (a,b) -> a[0]-b[0]);
        Arrays.sort(intervals, (a,b) -> a[0] == b[0] ? a[1]-b[1] : a[0]-b[0]);
        int ret = 0;
        
        //int[] prev = intervals[0]; just maintain prev end
        int prevEnd = Integer.MIN_VALUE;
        for(int i=0; i< intervals.length;i++) {
            int[] curr = intervals[i];
            if(curr[0] < prevEnd){
                ret++;
            } else {
                prevEnd = curr[1];
            }
        }

        return ret;
    }
    */

    /**
    rephrase problem - 
    "Given a collection of intervals, find the maximum number of intervals that are non-overlapping."
    -> if u remove a larger interval that overlaps, remaining ones will be max non - overlapping
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        int prevEnd = intervals[0][1];
        int count = 0;
        
        for (int i = 1; i < intervals.length; i++) {
            
            if (prevEnd > intervals[i][0]) {
                //there is overlap
                count++;
                //so prev or curr elem needs to be removed.
                //we keep elem with smaller endTime, to minimize overlaps
                //we remove one with bigger endtime, because that may overlap with coming intervals, thus give me min num to remove
                //we remove one with bogger end time whic hwill help maximize non-overlapping intervals
                prevEnd = Math.min(intervals[i][1], prevEnd);       //V imp - handles same star-time as well
            } else {
                prevEnd = intervals[i][1];
            }
        }
        return count;
    }
}
