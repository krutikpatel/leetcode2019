/*
Logger Rate Limiter

Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.

Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

It is possible that several messages arrive roughly at the same time.

Example:

Logger logger = new Logger();

// logging string "foo" at timestamp 1
logger.shouldPrintMessage(1, "foo"); returns true; 

// logging string "bar" at timestamp 2
logger.shouldPrintMessage(2,"bar"); returns true;

// logging string "foo" at timestamp 3
logger.shouldPrintMessage(3,"foo"); returns false;
*/
class Logger {

    /*
    We only need info of last 10 secs. anything beyond that will get printed by default.
    -Using Circular buffer to store last 10 sec buckets
    
    -For each sec, we have Set of Strings to see which logs were printed on that second.
    */
    
    Set[] sets;
    int[] circ_buf;
    
    /** Initialize your data structure here. */
    public Logger() {
        sets = new Set[10]; //imp: done in generic way so far.
        circ_buf = new int[10];
        
        for (int i = 0; i < sets.length; ++i) {
            sets[i] = new HashSet<String>();
        }
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        int slot = timestamp%10;
        
        //only clearing %10 slot.
        if(timestamp != circ_buf[slot]){
            //this is new value in same slot, ie, after 10 secs. So replace the slot
            circ_buf[slot] = timestamp;
            
            //we also clear correspondin bucket, because 10 secs have passed, so these all logs are irrevalent
            sets[slot].clear();            
        }                
        
        //need to check all 10 sec data for log message in ANY-case. If not in any bucket, this is new message for this 10 sec window.
        for (int i = 0; i < sets.length; ++i) {            
            
            //IMP: timestamp can be greater than 10 secs. 
            //if timestamp less that 10 sec old,  check if we already printed it somewhere
            if(timestamp - circ_buf[i] < 10 && sets[i].contains(message))
                return false;            
        }            
        
        sets[slot].add(message);
        return true;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
