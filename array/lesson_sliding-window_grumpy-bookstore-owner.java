/*
052. Grumpy Bookstore Owner
DescriptionHintsSubmissionsDiscussSolution

Today, the bookstore owner has a store open for customers.length minutes.  Every minute, some number of customers (customers[i]) enter the store, and all those customers leave after the end of that minute.

On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0.  When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.

The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.

Return the maximum number of customers that can be satisfied throughout the day.

 

Example 1:

Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
Output: 16
Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes. 
The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.

1 means grumpy, and grupmy means no sataisfied cust
*/
class Solution {
    
    /*
    Corner case: X = arr length
    */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int sum = 0;
        //calculate normal sum
        for(int i=0;i<customers.length;i++) {
            if(X==customers.length){
                sum+=customers[i];
            }
            else if(grumpy[i] == 0) {
                sum+=customers[i];
            }
        }
        
        if(X==customers.length)
            return sum;           
        
        //sliding window - going by max sum, other way is find max new ppl we can satisfy
        /*
        find: max new satisfiable cust
        maintain : prev window val (we dont want to calculate it each time for curr window, we will deduct from l, add from r)
        */          
        //make initial state of sligin window of X        
        int windowSum = sum;//used for sliding
        for(int i=0;i<X;i++) {
            if(grumpy[i] == 1) {
                windowSum+=customers[i];                
            }
        }
                
        //now slide. at beginnign i=0,j=X-1
        int retMax = windowSum;        
        int l = 1;
        int r = X;
        while(r<customers.length) {
            //dec l-1 if that was grupmy
            if(grumpy[l-1]==1){
                windowSum-=customers[l-1];
                //System.out.println("currSum,l="+currSum);
            }                
            //add r if grupmy    
            if(grumpy[r]==1){
                windowSum+=customers[r];
                //System.out.println("currSum,r="+currSum);
            }
            retMax = Math.max(retMax,windowSum);
            l++;
            r++;
        }
        return retMax;
    }
}

/*
one pass solution

    public int maxSatisfied(int[] customers, int[] grumpy, int X) {

        int satisfied = 0;
        int[] window = new int[customers.length];
        int left = 0;
        int right = 0;
        int max = 0;
        int windowSum = 0;

        for (int i = 0; i < customers.length; i++) {

            if (grumpy[i] == 1) {
                windowSum += customers[i];
                window[right] = customers[i];
                satisfied += 0;
            } else {
                window[right] = 0;
                satisfied += customers[i];

            }


            if (right >= X) {
                windowSum -= window[left];
                left++;
            }
            if (windowSum > max) {
                max = windowSum;
            }
            right++;

        }
        return satisfied + max;
    }
*/
