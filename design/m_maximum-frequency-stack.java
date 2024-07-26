/*
895. Maximum Frequency Stack
DescriptionHintsSubmissionsDiscussSolution

Implement FreqStack, a class which simulates the operation of a stack-like data structure.

FreqStack has two functions:

    push(int x), which pushes an integer x onto the stack.
    pop(), which removes and returns the most frequent element in the stack.
        If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.

 

Example 1:

Input: 
["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
Output: [null,null,null,null,null,null,null,5,7,5,4]
Explanation:
After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:

pop() -> returns 5, as 5 is the most frequent.
The stack becomes [5,7,5,7,4].

pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
The stack becomes [5,7,5,4].

pop() -> returns 5.
The stack becomes [5,7,4].

pop() -> returns 4.
The stack becomes [5,7].

*/
class FreqStack {

    /*
    pop removes only one occurency of element.
    
    Brute force
    push
        Map<elem,frequency>
    pop
        -sort entries by freq - if tie, the one closer to head/top wins. - that is order of insertion
        -return top elem
        -reduce that freq and keep taht entry in map
        -
    */
    HashMap<Integer,Integer> map;
    //PriorityQueue<Map.Entry<Integer,Integer>> pq; not easy to get single entry from map. only thing possible is map.entrySet, set does not offer get method !
    
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    int pushIndex;
    
    public FreqStack() {
        map = new HashMap<>();
        pq = new PriorityQueue<>((a,b)->{
                                 if(a.freq != b.freq)
                                    return b.freq-a.freq;
                                else 
                                    return b.pushIndex - a.pushIndex; //get bigger index, closer to top of stack, as inserted later/last
            /*
            Example:
            The stack is [4,7,5,7,5] top-bottom
            pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
            
            since it is stack : bigger the pushIndex, closer to top, and so we want bigger PushIndex first
            */
                                });
    }
    
    public void push(int x) {
        map.put(x,map.getOrDefault(x,0)+1);                
        pq.offer(new Pair(x,map.get(x),pushIndex));
        //System.out.println("push x :"+x+" , index ="+ pushIndex + " freq = "+map.get(x));
        pushIndex++;
    }
    
    public int pop() {
        //IMP - update map count for future push
        Pair p = pq.poll();
        map.put(p.key,map.get(p.key)-1);
        /*
        int ret = p.key;        
        int newFreq = p.freq-1;
        
        //update DS
        if(newFreq > 0){
            map.put(ret,newFreq);
            p.freq = newFreq;
            pq.offer(p);
        }
        return ret;
        */
        return p.key;
    }
    
    class Pair {
        int key;
        int freq;
        int pushIndex;
        public Pair(int key, int freq, int pushIndex){
            this.key = key;
            this.freq = freq;
            this.pushIndex = pushIndex;
        }
    }
    
    
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 */
