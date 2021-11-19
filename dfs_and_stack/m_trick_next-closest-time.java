/* medium
681. Next Closest Time
DescriptionHintsSubmissionsDiscussSolution

Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

Example 1:

Input: "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.

*/
class Solution {
    /*
    Since there are at max 4 * 4 * 4 * 4 = 256 possible times, just try them all...
    This brute-force is not bad at all for this problem, there is not much trick here you can apply..
    
    convert time into minutes, for comparisions
    WE ARE ONLY LOOKING FOR FUTURE CLOSEST TIME. NOT PAST
    
    DFS from each digit (make permutations)
    
    Best way to compaer time? convert to seconds ! :)
    */
    int diff = Integer.MAX_VALUE;
    String result = "";
    
    public String nextClosestTime(String time) {
        //so that we ONLY take/consider unique digits to create result
        Set<Integer> set = new HashSet<>();
        set.add(time.charAt(0)- '0');
        set.add(time.charAt(1)- '0');
        set.add(time.charAt(3)- '0');
        set.add(time.charAt(4)- '0');
        
        if (set.size() == 1) //same digits in time, cant do better/different
            return time;
        
        List<Integer> digits = new ArrayList<>(set);
        int minutes = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));

        dfs(digits, "", 0, minutes);

        return result;
    }

    private void dfs(List<Integer> digits, String cur, int pos, int target) {
        //if pos == 4, form time, calc diff and update result if needed
        if (pos == 4) {
            int curr = Integer.parseInt(cur.substring(0, 2)) * 60 + Integer.parseInt(cur.substring(2, 4));
            if (curr == target) 
                return;
            
            //take diff from target time ONLY future time - TRICK
            int currDiff = curr - target > 0 ? curr - target : 1440 + curr - target;//1440 is 24 hours. for -ve diff, consider next day that time, thus adding 24 hours
            
            if (currDiff < diff) {
                diff = currDiff;
                result = cur.substring(0, 2) + ":" + cur.substring(2, 4);
            }
            return;
        }

        //for each pos, try all digits - because many times repetetion of all digits allowed
        for (int i = 0; i < digits.size(); i++) {
            //avoid NOT VALID digits for each pos.
            if (pos == 0 && digits.get(i) > 2) 
                continue;
            if (pos == 1 && Integer.parseInt(cur) * 10 + digits.get(i) > 23) 
                continue;
            if (pos == 2 && digits.get(i) > 5) 
                continue;
            if (pos == 3 && Integer.parseInt(cur.substring(2)) * 10 + digits.get(i) > 59) 
                continue;
            
            dfs(digits, cur + digits.get(i), pos + 1, target);
        }
    }
}
