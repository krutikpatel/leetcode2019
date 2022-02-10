/* Medium
73. Set Matrix Zeroes
DescriptionHintsSubmissionsDiscussSolution

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:

Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]

Example 2:

Input: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]

Follow up:

    A straight forward solution using O(mn) space is probably a bad idea.
    A simple improvement uses O(m + n) space, but still not the best solution.
    Could you devise a constant space solution?
*/
class Solution {
    /*
    track 0 rows and cols and in next pass clear them
    */
    public void setZeroes(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        Set<Integer> rows = new HashSet<Integer>();
        Set<Integer> cols = new HashSet<Integer>();

        // Essentially, we mark the rows and columns that are to be made zero
        for (int i = 0; i < R; i++) {
          for (int j = 0; j < C; j++) {
            if (matrix[i][j] == 0) {
              rows.add(i);
              cols.add(j);
            }
          }
        }

        // Iterate over the array once again and using the rows and cols sets, update the elements.
        for (int i = 0; i < R; i++) {
          for (int j = 0; j < C; j++) {
            if (rows.contains(i) || cols.contains(j)) {
              matrix[i][j] = 0;
            }
          }
        }
    }
    
  ////O(1) space
  public void setZeroes(int[][] matrix) {
    //track zero rows and col
    boolean fr = false,fc = false;
    
    //find 0 elems, and make 1st elem of that row/col 0. use 1st row/col as indicator of 0 rather than extra storage map/set
    for(int i = 0; i < matrix.length; i++) {
        for(int j = 0; j < matrix[0].length; j++) {
            if(matrix[i][j] == 0) {
                if(i == 0) fr = true;
                if(j == 0) fc = true;
                matrix[0][j] = 0;
                matrix[i][0] = 0;
            }
        }
    }
    
    //using 0 on 1st elem row/col, turn curr elem as zero
    for(int i = 1; i < matrix.length; i++) {
        for(int j = 1; j < matrix[0].length; j++) {
            if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;
            }
        }
    }
    
    //needed to be done last. make enitre 1st row/col zero if fr or fc true
    if(fr) {
        for(int j = 0; j < matrix[0].length; j++) {
            matrix[0][j] = 0;
        }
    }
    if(fc) {
        for(int i = 0; i < matrix.length; i++) {
            matrix[i][0] = 0;
        }
    }
    
  }
  
    //================================ another way but not recommended
    
    //we dont want to touch original zeros, we want to clear for original zeros
    public void setZeroesOrig(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(visited[i][j] == false && matrix[i][j] == 0){
                    clear(i,j,matrix,visited);
                }
            }
        }
    }
    
    private void clear(int i,int j, int[][] mat, boolean[][] visited){
        int m = mat.length;
        int n = mat[0].length;
        
        for(int row = 0; row<m;row++){
            //we dont want to touch original zeros
            if(mat[row][j] != 0){
                visited[row][j] = true;    
            }
            mat[row][j] = 0;
            
        }
        
        for(int col = 0; col<n;col++){
            //we dont want to touch original zeros
            if(mat[i][col] != 0){
                visited[i][col] = true;    
            }
            mat[i][col] = 0;
            
        }
    }
  
}
