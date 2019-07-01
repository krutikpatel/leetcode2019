/*
Happy Number

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 

Input: 19
Output: true
Explanation: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
*/

/*
Technique:
if any of square num repeats, we are in circular queue.
Otherwise, keeps looking for 1

To check repeatation, we can do 2 things
1. maintain set of squares - memory intensive
2. maintain two pointers. slow and fast, this is just like finding loop/cycle in LL. this is faster as well.
*/

class Solution {
    public boolean isHappy(int n) {
/*
Set<Integer> seen = new HashSet<>();
int sq = getSquare(n);        
while(!seen.contains(sq)){            
    if(sq == 1)
        return true;
    seen.add(sq);
    sq = getSquare(sq); 
}

//if it was repeated
return false;
*/
        
        int fast= n;
        int slow=n;
        
        do{
            slow=getSquare(slow);
            fast=getSquare(getSquare(fast));
        }while(slow != fast);
        
        return slow == 1;        
    }
    
    private int getSquare(int x){
        int sum=0;
        while(x > 0){
            int digit = x%10;
            sum += digit*digit;            
            x = x/10;
        }
        //System.out.println(sum);
        return sum;
    }
}
