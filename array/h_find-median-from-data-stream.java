/* Hard
Find Median from Data Stream
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
*/
class MedianFinder {

    /*
    Algo:
    -we try to keep 2 lists, one has smaller half on nums, other has larger half of nums.
    -we achieve that by haveing 2 PQs, and balancing sizes at each insert/add
        -> like we see sorted array visually:
        -maxHeap PQ - reverse sorted order (max at top) - first half of stream
        -minHeap PQ - natural sorted order (min at top) - second half of stream
    -for median: just take top from both because its PQ.
    */
    
    Queue<Integer> maxHeap;
    Queue<Integer> minHeap;
    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
    }
    
    /*
    -decide which half side this num belongs
    if size mismatched, balance
    
    balance:
    take one from the top of bigger size PQ, and add to other one
    */
    public void addNum(int num) {        
        
        //decide which half side this num belongs
        if(maxHeap.peek() == null || num < maxHeap.peek()){
            maxHeap.offer(num); //left half
        }else{
            minHeap.offer(num); //right half
        }
        
        //balance - if size mismatch > 1
        while(minHeap.size()-maxHeap.size() > 1 || minHeap.size()-maxHeap.size()<-1){
            
            if(minHeap.size() > maxHeap.size()){
                maxHeap.offer(minHeap.poll());
            }else{
                minHeap.offer(maxHeap.poll());
            }
        }
    }
    
    public double findMedian() {
        //even total nums
        if(minHeap.size() == maxHeap.size()) {
            return (double)(minHeap.peek()+maxHeap.peek())/2;
        } 
        //odd total nums, whichever side has one extra, that is median
        else if(minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return maxHeap.peek();
        }        
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
