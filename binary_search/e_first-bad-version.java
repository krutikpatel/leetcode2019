/* easy
First Bad Version

You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example:

Given n = 5, and version = 4 is the first bad version.

call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true

Then 4 is the first bad version. 
*/
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    /*
    better solution - not obvious: navigating right or left based on true or false can lead us to first bad vesion.
        key: end up at valid index and thats our answer
    we dont have to look for next elem to see where it shifted
    */
    public int firstBadVersion(int n) {
        int l = 1;
        int r = n;
        
        while(l<r){
            int mid = l + (r-l)/2;
            if(isBadVersion(mid)){
                //go left
                r = mid;
            }else{
                //go right because its good version
                l = mid+1;
            }
        }
        
        return l;
    }
    
    public int firstBadVersion_slower(int n) {
        //narrow down to good and next as bad version index.
        //need access to 2 elems each time
        
        int l = 1;
        int r = n;
        
        while(l<r){
            int mid = l + (r-l)/2;
            boolean res1 = isBadVersion(mid);
            boolean res2 = isBadVersion(mid+1);
            if(res1 == false && res2 == true)
                return mid+1;
            else if(res1 == true){
                //go left to look for last good version
                r = mid;
            } else {
                //go right towards first bad
                l = mid+1;
            }            
        }
        
        if(isBadVersion(l)){
            return l;
        }else
            return -1;
    }
}
