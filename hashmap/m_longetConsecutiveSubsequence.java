/*
https://leetcode.com/problems/longest-consecutive-sequence/description/

imp: its subsequence and its "consecutive"
-it can wrap around loop too


*/

class Solution {
/*  O(n)  
    We will use HashMap. The key thing is to keep track of the sequence length and store that in the boundary points of the sequence. For example, as a result, for sequence {1, 2, 3, 4, 5}, map.get(1) and map.get(5) should both return 5.

Whenever a new element n is inserted into the map, do two things:

See if n - 1 and n + 1 exist in the map, and if so, it means there is an existing sequence next to n. Variables left and right will be the length of those two sequences, while 0 means there is no sequence and n will be the boundary point later. Store (left + right + 1) as the associated value to key n into the map.
Use left and right to locate the other end of the sequences to the left and right of n respectively, and replace the value with the new length.
Everything inside the for loop is O(1) so the total time is O(n). Please comment if you see something wrong. Thanks.
*/    
    public int longestConsecutive(int[] num) {
        int res = 0;
        //<num,itsLongestStreakSoFar>
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : num) {
            if (!map.containsKey(n)) {
                int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;// because we are forming/llokg for "consecutive" seq, we check n-1 and n+1
                int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;
                // sum: length of the sequence n is in
                int sum = left + right + 1;
                map.put(n, sum);

                // keep track of the max length 
                res = Math.max(res, sum);

                //update farthest entries NOT neighbors only
                    // extend the length to the boundary(s)
                    // of the sequence
                    // will do nothing if n has no neighbors
                map.put(n - left, sum);//update farthest entry/num for n's consecutive seq
                map.put(n + right, sum);//update farthest entry/num for n's consecutive seq
                
                //following does not work!
                    //map.put(n - 1, sum);
                    //map.put(n + 1, sum);
            }
            else {
                // duplicates
                continue;
            }
        }
        return res;
    }
}
