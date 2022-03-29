/* medium
lesson:
two pointers,
some good observation trick
====================================
763. Partition Labels
DescriptionHintsSubmissionsDiscussSolution

A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:

Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.

*/
class Solution {
    /*
    2 pass solution:
        -Record the last occurence of each letter in map
        -Keep current interval window marked by begin and end pointers
            -keeptrack of furthest most index of occurence for any letter in current interval.
            -if that is = i, we dont have anymore repeating in future, so it ends here. 
                Note: We END it here rather than including more unique chars because we want as many unique intervals as we can.
    */
    public List<Integer> partitionLabels(String S) {
        if(S == null || S.length() == 0){
            return null;
        }
        
        //step1-Record the last occurence of each letter in map
        int[] lastIndexMap = new int[26];
        for(int i=0;i<S.length();i++){
            lastIndexMap[S.charAt(i) - 'a'] = i;
        }
        
        List<Integer> ll = new ArrayList();
        
        //window begin and end
        int begin = 0;
        int end = 0;
        
        //step2
        for(int i=0;i<S.length();i++){
            end = Math.max(end, lastIndexMap[S.charAt(i) - 'a']);//keep track of last most index of any letters in this substring
            //imp, no more char has repeated occurence after this index.
            if(end == i){
                //we dont have later occurence. so partition ends here
                ll.add(end-begin + 1); //+1 because indexing starts with 0
                begin = i+1; //or end+1
            }
        }
        
        return ll;
    }
}
