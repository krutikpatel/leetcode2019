/*
Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.

 

Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: true
 
*/
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals == null || intervals.length==0)
            return true;
        Arrays.sort(intervals, (a,b) -> a[1]-b[1]);
        int prevEnd = intervals[0][1];
        for(int i=1;i<intervals.length;i++) {
            int[] curr = intervals[i];
            if(curr[0] < prevEnd) {
                return false;
            } else {
                prevEnd = curr[1];
            }
        }
        return true;
    }
}
