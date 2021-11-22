/* medium
165. Compare Version Numbers
DescriptionHintsSubmissionsDiscussSolution

Compare two version numbers version1 and version2.
If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.

The . character does not represent a decimal point and is used to separate number sequences.

For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

You may assume the default revision number for each level of a version number to be 0. For example, version number 3.4 has a revision number of 3 and 4 for its first and second level revision number. Its third and fourth level revision number are both 0.

 

Example 1:

Input: version1 = "0.1", version2 = "1.1"
Output: -1

Example 2:

Input: version1 = "1.0.1", version2 = "1"
Output: 1

Example 3:

Input: version1 = "7.5.2.4", version2 = "7.5.3"
Output: -1

Example 4:

Input: version1 = "1.01", version2 = "1.001"
Output: 0
Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”

Example 5:

Input: version1 = "1.0", version2 = "1.0.0"
Output: 0
Explanation: The first version number does not have a third level revision number, which means its third level revision
*/
class Solution {
    /*
    Corners:
    same :
    1.0 and 1.000
    1.0 and 1.0.0
    1.01 and 1.001
    
    key to make soln easy
        -for diff length, we can use 0 for whichever finished.
    */
    /*
    Remember:  in java, dot is special char like slash, so to specify dot in reg-ex, we need to say" \\.
    */
    public int compareVersion(String version1, String version2) {
        
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        
        //System.out.println(v1.length);
        //System.out.println(v2.length);
        int i=0;
        int j=0;
        while(i<v1.length || j<v2.length) {
            int s1 = 0;
            if(i<=v1.length-1)
                s1 = getNum(v1[i]);
            int s2 = 0;
            if(j<=v2.length-1)
                s2 = getNum(v2[j]);
                  
            //System.out.println(s1);
            //System.out.println(s2 + "__");
            if(s1>s2)
                return 1;
            else if(s2>s1)
                return -1;
            
            i++;
            j++;
        }
        
        
        return 0;
    }
    
    private int getNum(String s) {
        int ret = 0;
        for(char c:s.toCharArray()) {
            if(ret ==0 && c == '0')
                continue;
            ret = ret*10 + c-'0';
        }
        return ret;
    }
}
