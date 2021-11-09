/* medium
981. Time Based Key-Value Store
DescriptionHintsSubmissionsDiscussSolution

Create a timebased key-value store class TimeMap, that supports two operations.

1. set(string key, string value, int timestamp)

    Stores the key and value, along with the given timestamp.

2. get(string key, int timestamp)

    Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
    If there are multiple such values, it returns the one with the largest timestamp_prev.
    If there are no values, it returns the empty string ("").

 

Example 1:

Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
Output: [null,null,"bar","bar",null,"bar2","bar2"]
Explanation:   
TimeMap kv;   
kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1   
kv.get("foo", 1);  // output "bar"   
kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"   
kv.set("foo", "bar2", 4);   
kv.get("foo", 4); // output "bar2"   
kv.get("foo", 5); //output "bar2"   

Example 2:

Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
Output: [null,null,null,"","high","high","low","low"]

 

Note:

    All key/value strings are lowercase.
    All key/value strings have length in the range [1, 100]
    The timestamps for all TimeMap.set operations are strictly increasing.
    1 <= timestamp <= 10^7
    TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.
*/
class TimeMap {
    /*
    key -> timesorted key,val pairs. pair<time,val>
    no need of pair class, we need TreeMap<time,val>
    
    Given: time is always increasing. so natural add order is sorted.
        -so we can just have list of Pairs, and later do binary search
    
    Lesson: we can use TreeMap<> like : HashMap<String,TreeMap<Integer,String>> map
        -the keySet() is sorted. to reverse sort : new TreeMap<>((a,b) -> b-a)); //its only key comparision
        -TreeMap provides methods like floorKey(), ceilingKey() very useful
        
    
    Note:
    Problem here in using Map is, we will not be able to do binary search on keySet easity. Iterator on keyset is sorted. so i did linear search.
    If we want to take advantage of binary search, use List.
    */
    class Pair {
        int time;
        String value;
        Pair(int time, String val) {
            this.time = time;
            this.value = val;
        }
    }
    
    HashMap<String,TreeMap<Integer,String>> map;
    //HashMap<String,List<Pair>> map;
    
    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();    
    }
    
    public void set(String key, String value, int timestamp) {
        TreeMap<Integer,String> tmap = map.getOrDefault(key, new TreeMap<>((a,b) -> b-a));//key comparision only.
        tmap.put(timestamp,value);        
        map.put(key,tmap);
    }
    
    public String get(String key, int timestamp) {
        if(!map.containsKey(key))
            return null;
        TreeMap<Integer,String> tmap = map.get(key);
        if(tmap.containsKey(timestamp)){
            return tmap.get(timestamp);
        }
                
        int prevTime = -1;                
        for(Integer time: tmap.keySet()) {
            //System.out.println(time);
            if(time < timestamp){
                prevTime = time;                
                break;
            }
        }
        
        
        if(prevTime == -1)
            return "";
        else
            return tmap.get(prevTime);        
        
        /*
        
        //binary search for time. hoping this list will be sorted because of treemap - false
        List<Integer> list = new ArrayList<>(tmap.keySet());
        System.out.println(list);
        System.out.println(timestamp);
        if(list.size() > 0 && list.get(0) > timestamp) {
            return "";
        }
        
        int l = 0;
        int r = list.size()-1;
        while(l<=r){
            int mid = (l+r)/2;
            if(list.get(mid) == timestamp)
                return tmap.get(mid);
            if(list.get(mid) < timestamp){
                l++;
            } else {
                r--;
            }
        }
                
        return tmap.get(list.get(l));
        */
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
