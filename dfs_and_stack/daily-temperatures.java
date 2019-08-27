/*
Daily Temperatures
DescriptionHintsSubmissionsDiscussSolution

Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100]. 
*/
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] ret = new int[T.length];
        
		//store indices of temps, stack will look as ascending list of values. if new val higher remove all smaller vals from stack, as new value will help hit higher temp first.
		//if new val smaller, store in stack, as it might help other smaller val hit high temp first.
        Stack<Integer> s = new Stack();
        
        //start reverse
        for(int i = T.length -1;i>=0; i--){
            while(!s.isEmpty() && T[i] >= T[s.peek()]){ //consider equal as well coz newer elem will hit first 
                s.pop();
            }
            
            if(s.isEmpty())
				ret[i] = 0;
			else	
				ret[i] = s.peek()-i;
			
			s.push(i);//every index getss pushed once, but next time might get popped out if smaller 
        }
        
        return ret;
    }
}
