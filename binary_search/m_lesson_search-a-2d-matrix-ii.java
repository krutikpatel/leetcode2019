/* medium
240. Search a 2D Matrix II
DescriptionHintsSubmissionsDiscussSolution

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

    Integers in each row are sorted in ascending from left to right.
    Integers in each column are sorted in ascending from top to bottom.

Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

Given target = 5, return true.

Given target = 20, return false.

*/
class Solution {
    /*
    Take advantage that its sorted.
    Either start with last row or col.
        -and keep narrowing down
    
    -beginning with last row is more convenient than beginning with last col
    
    Each row is sorted : so if mat[row][0] > x, that row will not have it. so row--
    Each col is sorted : so if mat[row][col] < x, anything above that in that column will not have it, so col++
    */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0)
            return false;
        
        //start from bottom - left
        int row = matrix.length-1;
        int col = 0;
        
        while(row >= 0 && col < matrix[0].length){
            if(matrix[row][col] == target)
                return true;
            else if(matrix[row][col] > target) { //move up
                //row'th row does not have it
                row--;
            } else {
                //move right
                //col'th column does not have it
                col++;
            }
        }
        
        return false;
    }
}
