/*
1086. High Five
DescriptionHintsSubmissionsDiscussSolution

Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.

Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.  The average score is calculated using integer division.

 

Example 1:

Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
Output: [[1,87],[2,88]]
Explanation: 
The average of the student with id = 1 is 87.
The average of the student with id = 2 is 88.6. But with integer division their average converts to 88.

*/
class Solution {
    /*
    -iterate over students
    Map<id,pq>
    
    -for each student, get average (pq poll till 5)
    
    n students, m scores
    O(n*mlongm) + (O(n)*5)    
    
    -More clever this:
    just sort items array beforehand, by score. 
    then for any candidate, we are bound to have scrore in descending order. take 1st five and average
    */
    public int[][] highFive(int[][] items) {
        HashMap<Integer,PriorityQueue<Integer>> scoreMap = new HashMap<>();
        
        //prepare map
        for(int[] item:items) {
            PriorityQueue<Integer> currList = scoreMap.getOrDefault(item[0], new PriorityQueue<>((a,b)->b-a)); //maxheap
            currList.offer(item[1]);
            scoreMap.put(item[0],currList);
        }
        
        //prepare avg and return
        int size = scoreMap.entrySet().size();
        int[][] ret = new int[size][2];
        int i = 0;
        for(Map.Entry<Integer,PriorityQueue<Integer>> entry : scoreMap.entrySet()) {
            ret[i] = new int[]{entry.getKey(),getAverage(entry.getValue())};
            i++;
        }
        return ret;
    }
    
    private int getAverage(PriorityQueue<Integer> pq) {
        int i=0;
        int sum = 0;
        while(!pq.isEmpty() && i<5) {
            sum+=pq.poll();
            i++;
        }
        return sum/5;
    }
}
