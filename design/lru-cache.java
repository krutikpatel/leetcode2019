/*
146. LRU Cache
DescriptionHintsSubmissionsDiscussSolution

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

*/
    
class LRUCache {
/*
Req:
-capacity
-key,val plus order of access atleast ( we dont need time accessed, order is enough)
-get and put both updates access order
	-since we dont need "timestamp" order , we dont do PQ<time,node>

TLDR: HashMap as store, plus DoublyLinkedList nodes.

=>we just need to keep elems in order of which there were accessed.
->DLL - need head and tail.
->Map<key,DllNode>
	
	put{
		-if in map, move to front 
			-else add to front 
	
		if map size > capacity:
			-remove last elem, remove from map too
	}
	get{ 
		-if in map, return 
		-move elem to front 
	}
	
-to make get elem from DLL O(1) - use map 
	map<key,DLL>
	rather than map<key,val>
	
*/
    
/*    -leverage JAva LinkedHashMap. good for understanding concept, not for interview

    HashMap<Integer,Integer> map;
    int capacity;
    
    public LRUCache(int capacity) {
        map = new LinkedHashMap<Integer,Integer>(capacity, 0.75f, true){
                //override method. method returns true if if shud remove least recently inserted entry. i think called during put
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > capacity;
                }
            };
        this.capacity = capacity;
    }
    
    public int get(int key) {
        return map.getOrDefault(key,-1);
    }
    
    public void put(int key, int value) {
        map.put(key,value);
    }
*/
    /*
    TLDR: HashMap as store, plus DoublyLinkedList nodes.
    Logic:
    
    HashMap will only store nodes = capacity (and those will be LRU node)
    Use DLL to keep track of what is used most recently
    
    DLL needs following methods four our purposes
    -addNode
    -removeNode
    -moveToFront
    
    class vars : maintain head and tail of DLL.
    
    addNode()
        -create new node
        -insert after head
    
    removeNode()
        -remove before tail
    
    moveToFront() -> from anywhere
        -removeNode()
        -addNode()
        
    get()
        -check if exists in map, if so return
        -move this node to front of DLL
    
    put()
        -add to map and DLL
        -If size exceeded, removeNode from tail
    */
    //class vars
    DLLNode head;
    DLLNode tail;
    HashMap<Integer,DLLNode> map;    
    final int CAPACITY;
    int count;
    //
    
    class DLLNode {
        int key;
        int val;
        DLLNode prev;
        DLLNode next;
        
        public DLLNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    void addNode(DLLNode n) {
        //insert after head
        DLLNode temp = head.next;
        head.next = n;
        n.prev = head;
        
        n.next = temp;
        temp.prev = n;
    }
    
    void removeNode(DLLNode n) {        
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }
    
    void moveToFront(DLLNode n){
        removeNode(n);
        addNode(n);
    }
    
    ////////////
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.CAPACITY = capacity;
        count = 0;
        
        //initialize empty list, bounded by Head and Tail
        head = new DLLNode(-11,-11);
        tail = new DLLNode(-11,-11);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
            DLLNode n = map.get(key);
            //System.out.println("get: before front = "+head.next.key);
            //System.out.println("get: before tail = "+tail.prev.key);
            moveToFront(n);
            //System.out.println("get: after front = "+head.next.key);
            //System.out.println("get: after tail = "+tail.prev.key);
            return n.val;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            DLLNode n = map.get(key);
            n.val = value;
            moveToFront(n);
        } else {
            DLLNode n = new DLLNode(key,value);
            
            //add new node
            addNode(n);
            map.put(key,n);
            //count++;
            //if(count == CAPACITY) {
            if(map.size() > CAPACITY) {
                //LRU
                DLLNode nodeToRemove = tail.prev;
                //System.out.println("nodeToRemove = "+nodeToRemove.key);
                //remove from mp as well
                map.remove(nodeToRemove.key);
                
                //remvoe from DLL
                removeNode(nodeToRemove);
            }
        }
        
//        System.out.println(count);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
