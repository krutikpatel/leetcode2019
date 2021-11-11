/* medium
74. Search a 2D Matrix
DescriptionHintsSubmissionsDiscussSolution

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

    Integers in each row are sorted from left to right.
    The first integer of each row is greater than the last integer of the previous row.

Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
*/
class Solution {
    /*
    Don't treat it as a 2D matrix, just treat it as a sorted list
    -they are just rows that you can concatenate to form single sorted list
    */  
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length ==0)
            return false;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int l = 0;
        int r = m*n-1;
        while(l<=r){
            int mid = l + (r-l)/2;
            
            int row = mid/n;
            int col = mid%n;
            
            if(matrix[row][col] == target)
                return true;
            if(matrix[row][col] > target) {
                //go left
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return false;
    }
}
