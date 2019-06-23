/*
01 Matrix

Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

 

Example 1:

Input:
[[0,0,0],
 [0,1,0],
 [0,0,0]]

Output:
[[0,0,0],
 [0,1,0],
 [0,0,0]]

Example 2:

Input:
[[0,0,0],
 [0,1,0],
 [1,1,1]]

Output:
[[0,0,0],
 [0,1,0],
 [1,2,1]]

 

Note:

    The number of elements of the given matrix will not exceed 10,000.
    There are at least one 0 in the given matrix.
    The cells are adjacent in only four directions: up, down, left and right.

*/

class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return matrix;
        
        int unvisited = Integer.MAX_VALUE;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        Queue<int[]> q = new LinkedList();
        
        //mark all 1's with MAX, so that we can use it to see unvisited nodes
        //Also, BFS from all 0's. ie, start from end/ans/desired point. So add 0's to q
        for(int i = 0;i < matrix.length;i++){
            for(int j = 0;j < matrix[0].length; j++){
                if(matrix[i][j] == 1)
                    matrix[i][j] = unvisited;
                else// if(matrix[i][j] == 0){
                    q.offer(new int[]{i,j});//we actually want to stor both i,j as one node
                                
            }
        }
        
        //now BFS from all 0's. ie, start from end/ans/desired point
        while(!q.isEmpty()){
            int[] point = q.poll();
            int i = point[0];
            int j = point[1];
            
            int curr = matrix[i][j];
            
            //BFS - if unvisited - replace with curr+1. add to queue to help some other node to reach 0
            //System.out.println("curr = " + curr + "   i=" + i + "j="+j);
            
            //up
            if(i-1>=0){
                if(matrix[i-1][j] == unvisited){
                    matrix[i-1][j] = curr+1;
                    //System.out.println("up" + (curr+1));
                    q.offer(new int[]{i-1,j});
                }
            }
            //down
            if(i+1<m){
                if(matrix[i+1][j] == unvisited){
                    matrix[i+1][j] = curr+1;
                    q.offer(new int[]{i+1,j});
                    //System.out.println("down" + (curr+1));
                }
            }
            //left
            if(j-1>=0){
                if(matrix[i][j-1] == unvisited){
                    matrix[i][j-1] = curr+1;
                    q.offer(new int[]{i,j-1});
                    //System.out.println("left" + (curr+1));
                }
            }
            //right
            if(j+1<n){
                if(matrix[i][j+1] == unvisited){
                    matrix[i][j+1] = curr+1;
                    q.offer(new int[]{i,j+1});
                    //System.out.println("right" + (curr+1));
                }
            }
        }
        
        return matrix;       
    }
}
