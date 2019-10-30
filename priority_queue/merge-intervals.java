/*
Merge Intervals
DescriptionHintsSubmissionsDiscussSolution

Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
*/
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0)
            return new int[0][0];
        
        //Sort intervals by start time to use them in chronological order. so that they can be merged
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });
        
        List<int[]> ll = new ArrayList();
        int[] prev = intervals[0];
        ll.add(prev);
        for(int i=1;i<intervals.length;i++){
            if(prev[1] >= intervals[i][0]){
                //merge, whichever endtime bigger,use that
                prev[1] = prev[1] >= intervals[i][1] ? prev[1] : intervals[i][1];                
            }else{
                //add new interval
                ll.add(intervals[i]);
                prev = intervals[i];
            }
        }
        
        return ll.toArray(new int[ll.size()][2]);
    }
}
