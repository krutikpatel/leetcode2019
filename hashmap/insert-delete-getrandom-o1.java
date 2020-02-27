/*
380. Insert Delete GetRandom O(1)
DescriptionHintsSubmissionsDiscussSolution

Design a data structure that supports all following operations in average O(1) time.

    insert(val): Inserts an item val to the set if not already present.
    remove(val): Removes an item val from the set if present.
    getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
*/
class RandomizedSet {

    /*
    -use 
    1. HashMap<val, indexInArrayList> - we need to map indexes, because when remove is called, we dont know where that elem is in arraylist.
    2. ArrayList - as main place for nums - we will use ArrayList so that we can use Math.random(list.size) to get random index.
        -that creates problem of removel from ArrayList. for that do following:
        -to remove from ArrayList: swap with last num, and remove last one. so that we dont messup indexes of remaining elems in list
    */
    HashMap<Integer,Integer> map;
    ArrayList<Integer> nums;
    java.util.Random rand = new java.util.Random();
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        nums = new ArrayList<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val))
            return false;
        
        nums.add(val);
        map.put(val,nums.size()-1);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val))
            return false;
        
        int index = map.get(val);
        if(index < nums.size()-1) {
            //swap with last elem in list. then remove last from list.
            //update last's new location in map.
            int lastElem = nums.get(nums.size()-1);
            nums.set(index,lastElem);   //note: modifying existing location, NOT add.
            map.put(lastElem, index);
        }
        
        nums.remove(nums.size()-1);
        map.remove(val);
        
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int retIndex = rand.nextInt(nums.size());
        return nums.get(retIndex);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
