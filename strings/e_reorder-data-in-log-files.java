/* easy
937. Reorder Data in Log Files
DescriptionHintsSubmissionsDiscussSolution

You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

    Each word after the identifier will consist only of lowercase letters, or;
    Each word after the identifier will consist only of digits.

We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.

Reorder these logs so that:

The letter-logs come before all digit-logs.
The letter-logs are sorted lexicographically by their contents. If their contents are the same, then sort them lexicographically by their identifiers.
The digit-logs maintain their relative ordering.

Return the final order of the logs.

 

Example 1:

Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]

*/
class Solution {
    /*
    -put letter logs in PQ
    -put digit logs in ArrayList to maintain order
    */
    public String[] reorderLogFiles(String[] logs) {
        ArrayList<String> digit = new ArrayList<>();
        PriorityQueue<String> pq = new PriorityQueue<>(
            (a,b)->{
                String x = a.substring(a.indexOf(' ')+1,a.length());
                String y = b.substring(b.indexOf(' ')+1,b.length());
                if(x.equals(y)){
                    return a.substring(0,a.indexOf(' ')).compareTo(b.substring(0,b.indexOf(' ')));
                } else 
                    return x.compareTo(y); 
        });
        
        for(String s:logs){
            int index = s.indexOf(' ')+1;
            if(Character.isDigit(s.charAt(index))){
                digit.add(s);
            } else {
                pq.offer(s);
            }
        }
        
        String[] ret = new String[logs.length];
        int i=0;
        while(!pq.isEmpty()){
            ret[i] = pq.poll();
            i++;
        }
        for(String s:digit) {
            ret[i] = s;
            i++;
        }
        return ret;
    }
}
