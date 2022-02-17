/*
1094. Car Pooling
DescriptionHintsSubmissionsDiscussSolution

You are driving a vehicle that has capacity empty seats initially available for passengers.  The vehicle only drives east (ie. it cannot turn around and drive west.)

Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip: the number of passengers that must be picked up, and the locations to pick them up and drop them off.  The locations are given as the number of kilometers due east from your vehicle's initial location.

Return true if and only if it is possible to pick up and drop off all passengers for all the given trips. 

 

Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false

Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true

Example 3:

Input: trips = [[2,1,5],[3,5,7]], capacity = 3
Output: true

Example 4:

Input: trips = [[3,2,7],[3,7,9],[8,3,9]], capacity = 11
Output: true
*/
class Solution {
    /*
    Nice neat approach:
    
    1.Save all time points and the change on current capacity
        map<changePoint, passengers+->
        -for start : +passengers
        -for end: -passengers
        
    2.Sort all the changes on the key of time points. 
        ---using treemap to do this 
    3.Track the current capacity and return false if negative

Complexity

Time O(NlogN)
Space O(N)

class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> m = new TreeMap<>();
        
        for (int[] t : trips) {
            //+ for start point
            m.put(t[1], m.getOrDefault(t[1], 0) + t[0]);
            //- for end point
            m.put(t[2], m.getOrDefault(t[2], 0) - t[0]);
        }
        
        //if at any point, net passengers is -ve (means we filled more that capacity)
        for (int v : m.values()) {
            capacity -= v;
            if (capacity < 0) {
                return false;
            }
        }
        return true;
    }
}


    */
    
    /*
    -doing same start and end checkpoints (and +- capacity at each point) with array and PQ
    IMP - if we are tracking passenger loading capacity, 
        it is v imp to add capacity by first considering passengers leaving because if end point
    */
    public boolean carPooling2(int[][] trips, int capacity) {
        //sort trip by start time (covers start point events)
        Arrays.sort(trips, (a,b) -> a[1] - b[1]);
        
        //we will add nodes which we crossed here, sorted by end time (this will add checkpoint at end event)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[2]-b[2]);
                
        for(int[] trip: trips){
            //add in pq for end sort
            pq.offer(trip);
         
            //add capacity before dec capacity, because there might be people getting off here
            //remove elems till endtime <= curr_start_time
            while(!pq.isEmpty() && pq.peek()[2] <= trip[1]) {
                //int[] ending = pq.poll();
                capacity += pq.poll()[0];
            }
            
            //dec capacity
            capacity -= trip[0];
            if(capacity<0){
                return false;
            }
        }
            
        
        return true;
    }
    
    /*
    -Basically we just want to do +- capacity at each event points
    So, we can do that with single list with all event points too
    */
    /*
    public boolean carPooling(int[][] trips, int capacity) {
        List<int[]> times = new ArrayList<>();
        for (int i = 0; i < trips.length; i++) {
            times.add(new int[]{trips[i][1], trips[i][0]});
            times.add(new int[]{trips[i][2], - trips[i][0]});
        }
        Collections.sort(times, (t1, t2) -> {
            if (t1[0] != t2[0]) return t1[0] - t2[0];
            else return t1[1] - t2[1];
        });
        int needed = 0;
        for (int[] t : times) {
            needed += t[1];
            if (needed > capacity) return false;
        }
        return true;
    }
    */
    
    /*
    maintain list<int[]> event-point and capacity+-
    if needed > capacity at any point, fail
    */    
    public boolean carPooling(int[][] trips, int capacity) {
        
        List<int[]> eventPoints = new ArrayList<>();
        for(int[] trip: trips) {
            //remove frpm capacity
            eventPoints.add(new int[]{trip[1], trip[0]});
            //add to capacity
            eventPoints.add(new int[]{trip[2], -trip[0]});
        }
        
        /*
        imp - if two points have same time, we need to offload first (inc capacity). so put end-event point first
        */
        Collections.sort(eventPoints, (a,b)->{ if(a[0] != b[0]) 
                                                return a[0]-b[0];
                                              else 
                                                return a[1]-b[1];});

        for(int[] stops:eventPoints) {
            capacity-=stops[1];
            if(capacity < 0)
                return false;
        }
        return true;
    }
    
}
