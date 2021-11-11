/* medium
intelligent use of 
  -binary search
  -map<id,count>
  -int[] to store maxSoFar, rahter than sorting map, because then u cant apply that special condition

*/
/*
911. Online Election
DescriptionHintsSubmissionsDiscussSolution

In an election, the i-th vote was cast for persons[i] at time times[i].

Now, we would like to implement the following query function: TopVotedCandidate.q(int t) will return the number of the person that was leading the election at time t.  

Votes cast at time t will count towards our query.  In the case of a tie, the most recent vote (among tied candidates) wins.

 

Example 1:

Input: ["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
Output: [null,0,1,1,0,0,1]
Explanation: 
At time 3, the votes are [0], and 0 is leading.
At time 12, the votes are [0,1,1], and 1 is leading.
At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
This continues for 3 more queries at time 15, 24, and 8.
*/
class TopVotedCandidate {
    /*
    1. There can be n persons for which we can vote
    persons[i] is vote for person = persion[i]
    
    2. indices of both array indicate same time-stamp
    
    times - we need max at that index, kind of lookup
    BUT, if there is no exact time match, we go for lower closer time. - need way to do this kind of search. - iteration is possible, but slower that binary search
    
    Problem-we need max voted person at each index i (max at that time)
    soln:
        a-we make HashMap<personId,vote_count>
        b-then we sort it by vote_count (but take most recent vote in-case of tie ??)
        bb-NO NEED TO SORT MAP
            -just keep track of max-person, whenever it changes we have new person. this way we can consider most-recent guy in case if tie too.
            -store winners in new array. winner at time-i
    */
    
    int[] winnerAtMoment;    
    int[] times;
    
    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        winnerAtMoment = new int[times.length];
        
        //track winnerSoFar. tracking max count does not help
        int winnerSoFar = -1;
        HashMap<Integer,Integer> personToVoteCount = new HashMap<>();
        for(int i=0;i<persons.length;i++) {
            int count = personToVoteCount.getOrDefault(persons[i],0);
            count++;
            personToVoteCount.put(persons[i],count);
            
            //winnerAtMoment
            if(winnerSoFar == -1) {
                winnerAtMoment[i] = persons[i];
                winnerSoFar = persons[i];
            } else {
                if(count>=personToVoteCount.get(winnerSoFar)){
                    //update winner
                    winnerAtMoment[i] = persons[i];
                    winnerSoFar = persons[i];
                } else {
                    winnerAtMoment[i] = winnerSoFar;
                }
            }
        }
    }
    
    /*
    binary search in time array, get index and fetch elem from winnerAtMoment
    */
    public int q(int t) {
        int index = getTimeFloorIndex(t);
        return winnerAtMoment[index];        
    }
    
    private int getTimeFloorIndex(int t) {
        //binary search
        /* finding normal way of doint it, template 2, using mid and mid+1
        int l = 0;                //imp
        int r = times.length-1;   //imp
        
        while(l<r) {
            int mid = l+(r-l)/2;
            
            if(times[mid] == t){
                return mid;
            } else if(times[mid] < t && times[mid+1] > t) {
                return mid;
            } else if(times[mid] < t && times[mid+1] < t) {
                r = mid;
            } else {
                //t < times[mid]
                l = mid+1;
            }                
        }
        return l;//l converges towards one elm higher
        */
        
        int l = 1;              //imp
        int r = times.length;   //imp
        
        while(l<r) {
            int mid = l+(r-l)/2;
            
            if(times[mid] == t){
                return mid;
            } else if(t>times[mid]) {
                l = mid+1;
            } else {
                //t < times[mid]
                r = mid;
            }                
        }
        return l-1;//l converges towards one elm higher
        
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
