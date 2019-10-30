/*
Exclusive Time of Functions
DescriptionHintsSubmissionsDiscussSolution

On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.

We store logs in timestamp order that describe when a function is entered or exited.

Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  "1:end:2" means the function with id 1 ended at the end of timestamp 2.

A function's exclusive time is the number of units of time spent in this function.  Note that this does not include any recursive calls to child functions.

The CPU is single threaded which means that only one function is being executed at a given time unit.

Return the exclusive time of each function, sorted by their function id.

 

Example 1:

Input:
n = 2
logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
Output: [3, 4]
Explanation:
Function 0 starts at the beginning of time 0, then it executes 2 units of time and reaches the end of time 1.
Now function 1 starts at the beginning of time 2, executes 4 units of time and ends at time 5.
Function 0 is running again at the beginning of time 6, and also ends at the end of time 6, thus executing for 1 unit of time. 
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.

Note:

    1 <= n <= 100
    Two functions won't start or end at the same time.
    Functions will always log when they exit.
*/
class Solution {
    /*
    Note:
    this is log, we cant sort it and modify the sequence.
    
    TO Simplify problem,
    -we break time into segmented intervals, (segmented by intermittent other tasks)
    -we keep stack of taskID that we encountered, so that we know last time was of what task
    
    -result arr index is taskID
    
    Use Stack to:
        store last taskId that got STARTED. 
        its Chronological list of task started
    */
    public int[] exclusiveTime(int n, List<String> logs) {
        // separate time to several intervals, add interval to their function
        int[] result = new int[n];
        
        //store last taskId that got STARTED. Chronological list of task started
        Stack<Integer> st = new Stack<>();
        int lastStart = 0; // pre means the start of prev task
        
        for(String log: logs) {
            String[] arr = log.split(":");
            int taskId = Integer.parseInt(arr[0]);
            int timestamp = Integer.parseInt(arr[2]);
            
            if(arr[1].equals("start")) {
                if(!st.isEmpty())  
                    result[st.peek()] += timestamp - lastStart;//accumulate exclusive time
                                
                st.push(taskId);
                lastStart = timestamp;
            } else {
                //its end - we dont try to match taskId with stack, it has to be it..
                result[st.pop()] += timestamp - lastStart + 1;
                
                lastStart = timestamp + 1;//this will be marker for exclusive time for next task
                
            }
        }
        return result;
    }
}
