/*
Task Scheduler
DescriptionHintsSubmissionsDiscussSolution

Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

 

Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.

*/
class Solution {
    /*
    There has to be n intervals before same task can be resused.
    For that period, We can  either
        -introduce idle OR
        -execure new task
    Eg
    A,A,A,B,B and n =2
    A ? ? A ? ? A ..
      B _   B _ 
    1 2 3 4 5 6 7 -> ans  

    The time complexity of the algorithm is 
    O(26log26+N), where 26log26 is the time complexity of sorting the frequency array, 
    and N is the length of the input task list, which is the dominating term.
    
    */
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0)
            return -1;
        	int[] freq = new int[26];
        for(char c: tasks){
            freq[c-'A']++;
        }

        Arrays.sort(freq);
        int ret = 0;
        
        while(freq[25]!=0){
            int interval = n+1;//fill these many slots
            for(int i=25;i>=0;i--){
                if(freq[i] > 0 && interval >0){
                    freq[i]--;
                    interval--;
                    ret++;
                } else {
                    // either interval ended OR tasks ended. in both case this iteration is done
                    break;
                }
            }
            
            Arrays.sort(freq);
            if(freq[25]==0){
                //ret--;//remove that tail cooling period    
                //nothing more can be done/ no need to append anything
                break;
            }
            ret+=interval;//add cooldown period. if interval ==0 there is no cooldown
        }
        return ret;
        
    }

    //----
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0)
            return -1;
        
        //build map to sum the amount of each task
        HashMap<Character,Integer> map = new HashMap<>();
        for (char ch:tasks){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        
        // build queue, sort from descending
        PriorityQueue<Map.Entry<Character,Integer>> queue = new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
        queue.addAll(map.entrySet());
        
        int time = 0;
        // when queue is not empty, there are remaining tasks
        while (!queue.isEmpty()){
            // for each interval
            int interval = n+1;
            // list used to update queue. tasks used to fill n intervals, we take them out of PQ so save here
            List<Map.Entry<Character, Integer>> list = new ArrayList<>();
    
            // fill the intervals with the next high freq task
            while (interval > 0 && !queue.isEmpty()){
                Map.Entry<Character,Integer> entry = queue.poll();
                entry.setValue(entry.getValue()-1);
                
                //save in list so that we can put back in PQ
                list.add(entry);
                // interval shrinks
                interval --;
                // one slot is taken
                time++;
            }
            
            // update the value in the map and add to PQ if > 0
            for (Map.Entry<Character,Integer> entry:list){
                // when there is left task
                if (entry.getValue() > 0)
                    queue.offer(entry);
            }
            
            // job done
            if (queue.isEmpty())
                break;
            // if interval is > 0, then the machine can only be idle, no tasks to interweave
            time += interval;
        }
        
        return time;
    }
    
    /////////////////////////////////////////////////
    public int leastInterval2(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int time = 0;
        
        //because sorted, last elem has to be > 0 if we r not done yet
        while (map[25] > 0) {
            //System.out.println("map[25] = "+map[25]);
            
            //arr index, to pick task. start picking from max occuring task, to least
            int i = 0;
            
            //after n intervals, we can reuse curr elem to avoid n cooling period for each SAME task at end !
            //finish placing map[25] (MOST OCCURING) elem/task
            while (i <= n && map[25] != 0) {
                if (i < 26 && map[25 - i] > 0){
                    map[25 - i]--; //reduce used task. we ONLY go till n tasks, then we can repeat our curr task.
                    //System.out.println("        task reduced = "+ (25-i));
                    
                    time++; //task
                }else{
                    //put idle time, since no valid task available. spot before repeated elem can be used. to avoid n idle spaces
                    time++; //idle
                    //System.out.println("        idle inserted");
                }                                    
                i++;    //to pick new elem (next max occuring, map[24], 23 and so on .. )
                
                //System.out.println("    i="+i+" and time = "+ time);
            }
            //need to sort again after n intervals, because frequencies might have gotten out of order
            Arrays.sort(map);
        }
        return time;
    }
    
}
