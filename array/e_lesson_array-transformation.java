/* Easy
1243. Array Transformation
DescriptionHintsSubmissionsDiscussSolution

Given an initial array arr, every day you produce a new array using the array of the previous day.

On the i-th day, you do the following operations on the array of day i-1 to produce the array of day i:

    If an element is smaller than both its left neighbor and its right neighbor, then this element is incremented.
    If an element is bigger than both its left neighbor and its right neighbor, then this element is decremented.
    The first and last elements never change.

After some days, the array does not change. Return that final array.

 

Example 1:

Input: arr = [6,2,3,4]
Output: [6,3,3,4]
Explanation: 
On the first day, the array is changed from [6,2,3,4] to [6,3,3,4].
No more operations can be done to this array.

*/
class Solution {
    /*
    every time , we haev new resulting array.
    we dont ahve to creatw new array eveytime, 
        -need arr ref reassignment between 2 arrays,
    
    Given A[]
    -create B[] inide loop.
    -work on A, put result in B
    -at end, A=B
    
    */
    public List<Integer> transformArray(int[] arr) {
        
        boolean changed = false;
        //while(changed){
        do{
            changed = false;
            int[] temp = new int[arr.length];
            //first and last never change so need to copy
            temp[0] = arr[0];
            temp[arr.length-1] = arr[arr.length-1];
            
            for(int i=1;i<arr.length-1;i++) {
                if(arr[i] < arr[i-1] && arr[i] < arr[i+1]){
                    temp[i] = arr[i]+1;
                    changed = true;
                }else if(arr[i] > arr[i-1] && arr[i] > arr[i+1]){
                    temp[i] = arr[i]-1;
                    changed = true;
                } else {
                    temp[i] = arr[i];
                }
            }
            
            //reassign result for next operation
            arr = temp;
            //for(int i:arr)
            //    System.out.print(i);
            //System.out.println(changed);
        } while(changed);
        
        List<Integer> ret = new ArrayList<>();
        for(int i:arr)
            ret.add(i);
        return ret;
    }
}
