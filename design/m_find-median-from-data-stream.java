/*
295. Find Median from Data Stream
DescriptionHintsSubmissionsDiscussSolution

Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
For example,

[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

    void addNum(int num) - Add a integer number from the data stream to the data structure.
    double findMedian() - Return the median of all elements so far.

 

Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2

 

Follow up:

    If all integer numbers from the stream are between 0 and 100, how would you optimize it?
    If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?


*/
class MedianFinder {

    /*
    Algo:
    -we try to keep 2 lists, one has smaller half on nums, other has larger half of nums.
    -we achieve that by haveing 2 PQs, and balancing sizes at each insert/add
        -> like we see sorted array visually:
        -small PQ - reverse sorted order (max at top)
        -larger PQ - natural sorted order (min at top)
    -for median: just take top from both because its PQ.
    */
    /*
    for followup
    -we maintain HashMap<number,frequency>
    -we also maintain total number of elements, so that median is just total-nums/2 th element from sorted array.
    -then we go over Map from 1-100 and count element, when we hit total-nums/2 th element , that is our median

    */
    Queue<Integer> small;
    Queue<Integer> large;
    /** initialize your data structure here. */
    public MedianFinder() {
        large = new PriorityQueue<Integer>();
        small = new PriorityQueue<Integer>(Collections.reverseOrder());
    }
    
    /*
    -decide which half side this num belongs
    if size mismatched, balance
    
    balance:
    take one from the top of bigger size PQ, and add to other one
    */
    public void addNum(int num) {        
        
        //decide which half side this num belongs
        if(large.peek()==null || num>large.peek()){
            large.offer(num);
        }else{
            small.offer(num);
        }
        
        //balance - if size mismatch > 1
        while(large.size()-small.size() > 1 || large.size()-small.size()<-1){
            
            if(large.size() > small.size()){
                small.offer(large.poll());
            }else{
                large.offer(small.poll());
            }
        }
    }
    
    public double findMedian() {
        //System.out.println(large);
        
        if(large.size() == small.size()){
            return (large.peek() + small.peek()) / 2.0;
        }
        else if(large.size() > small.size()){
            return large.peek();
        } else {
            return small.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
