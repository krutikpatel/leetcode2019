/*
Two Sum III - Data structure design
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

Example 1:

add(1); add(3); add(5);
find(4) -> true
find(7) -> false

Example 2:

add(3); add(1); add(2);
find(3) -> true
find(6) -> false

*/


/*
Note:
simple HashMap approach is slower than O(n) for sum find.

So we choose to maintain list of nums. Then do binary search for sum elem !
*/
class TwoSum {

    Map<Integer, Integer> map = new HashMap<>();
    
    /** Initialize your data structure here. 
    public TwoSum() {
        
    }
    */
    
    /** Add the number to an internal data structure.. */
    public void addSlower(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean findSlower(int value) {
        for(int key : map.keySet()) {
            int remain = value - key;
            if (remain == key && map.get(key) >= 2) return true;
            if (remain != key && map.containsKey(remain)) return true;
        }
        return false;
    }
    
    ///////////////////// faster with binary search ////////////////////////////
    List<Integer> l;
    /** Initialize your data structure here. */
    public TwoSum() {
        l = new ArrayList<Integer>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        l.add(number);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        Collections.sort(l);
        int left = 0, right = l.size()-1;
        while(left < right) {
            if( (l.get(left) + l.get(right)) == value) { 
                return true; 
            }
            else if( (l.get(left) + l.get(right)) > value) {
                right--; 
            } else {
                left++;
            }            
        }
        return false;
    }
}
