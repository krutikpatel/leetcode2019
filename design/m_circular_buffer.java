/*
https://leetcode.com/problems/design-circular-queue/

*/
/**
Note: circular buffer or ring buffer
    -as name suggests, its a buffer. And normally we dont overwrite buffer, we let consumer consume first
    -if buffer is full, writer cant write. Consumer has to consume first

    Also, normally its good idea to add thread protection on buffer using lock
 */
class MyCircularQueue {
    Queue<Integer> q;
    Integer rear;
    int k=0;
    private ReentrantLock queueLock = new ReentrantLock();
  
    public MyCircularQueue(int k) {
        q = new LinkedList<>();
        this.k = k;
    }
    
    public boolean enQueue(int value) {
      queueLock.lock();
      try {
          if(q.size() == k){
              return false;
          }
          q.offer(value);
          rear = value;
        
      } finally {
         queueLock.unlock();
      }
        return true;
    }
    
    public boolean deQueue() {
        if(q.isEmpty()){
            return false;
        }
        q.poll();
        return true;
    }
    
    public int Front() {
        if(q.isEmpty()){
            return -1;
        }
        return q.peek();
    }
    
    public int Rear() {
        if(q.isEmpty()){
            return -1;
        }
        return rear;
    }
    
    public boolean isEmpty() {
        return q.isEmpty();
    }
    
    public boolean isFull() {
        return q.size() == k;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
