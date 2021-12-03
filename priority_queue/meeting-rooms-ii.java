/*
253. Meeting Rooms II
DescriptionHintsSubmissionsDiscussSolution

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2

Example 2:

Input: [[7,10],[2,4]]
Output: 1
*/
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0)
            return 0;
        
        //Sort intervals by start time to use them in chronological order.
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });
        
        //Make PQ min heap by end-time to access earaliest expiring room to re-use.
        //actually we are only using end time from intervals in pq. so we only need pq<Integer>
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[1] - b[1];
            }
        });
        
        pq.offer(intervals[0]);
        
        //simpler
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0] >= pq.peek()[1]){ //if new start-time >= end-time from pq, re-use room
                pq.poll();//remove this interval - room can be reused
            }
            pq.offer(intervals[i]);
        }
        
        /*
        for(int i=1;i<intervals.length;i++){
            int[] curr = pq.poll();
            if(intervals[i][0] >= curr[1]){ //if new start-time >= end-time from pq, re-use room
                curr[1] = intervals[i][1];
            }else{
                //new room, ie, add this new interval to PQ
                pq.offer(intervals[i]);
            }
            
            //add curr back to pq in either case
            pq.offer(curr);
        }
        */
        return pq.size();
    }
}
