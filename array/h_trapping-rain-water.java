/* Hard
trick
and intelligent use of stack

42. Trapping Rain Water
DescriptionHintsSubmissionsDiscussSolution

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

*/
class Solution {
    
    /*
    Stack based solution
    -it you try go left to right, take each water slot.
        -what you need is leftBoundary and curr. you do that when curr > leftBoundary
        -BUT, we need to save all smaller elems after leftBuondary, in LiFO order,  for future use.
            -because we will be calculating water for smaller one first.
            -for lifo, use stack
    */
    public int trap(int[] A) {        
        if (A==null) 
            return 0;
        
        //store indexes of smallerLeftBoundary
        Stack<Integer> s = new Stack<Integer>();
        
        int i = 0, total = 0;
        while (i < A.length){
            if (s.isEmpty() || A[i]<=A[s.peek()]){
                s.push(i);
                i++;
            }
            else {
                int bottomIndex = s.pop();//stack's top is bottom level, or levevl where water sits
                int bottomLevel = A[bottomIndex];
                int currWater = 0;
                
                if(!s.isEmpty()) {  //imp check, because we already popped once (visual : _| rather than |_| )
                    int left = s.peek();
                    int leftLevel = A[s.peek()];//left boundary. A[i] is right
                    int rightLevel = A[i];

                    currWater = (Math.min(leftLevel, rightLevel) - bottomLevel) * (i-left-1);
                    total += currWater;

                      
                }
                
                //imp: we dont push right side here as future left
            }
        }
        return total;
    }
    
    
    public int trap1(int[] height) {
        
        /*
        maintain min and max
        if next >= max
        else if next == min
        
        */
        
        int l = 0;
        int r = height.length-1;
        int lmax = 0;
        int rmax = 0;
        
        int sum = 0;        
        while(l<=r) {
            lmax = Math.max(lmax,height[l]);       
            rmax = Math.max(rmax,height[r]);       
            
            //proceed smaller side
            if(lmax < rmax) {
                sum += (lmax - height[l]);
                l++;
            } else {
                sum += (rmax - height[r]);
                r--;
            }
        }
        
        return sum;
    }
    
    //with extra memory, easy to understand
    public int trap2(int[] A) {    
        if(A.length==0) return 0;

        //track max_i from left and right in 2 arrays
        int[] A1=new int[A.length];
        int[] A2=new int[A.length];
        
        int max=A[0];
        for(int i =0;i<A.length;i++) {
            if(A[i]>max) max=A[i];
            A1[i]=max;
        }
        max=A[A.length-1];
        for(int i=A.length-1;i>=0;i--){
            if(A[i]>max) max=A[i];
            A2[i]=max;
        }
        
        int sum=0;
        //take sum with smaller height
        for(int i=0;i<A.length;i++){
         sum=sum+Math.min(A1[i],A2[i])-A[i];
        }
        return sum;
    }        
    
}
