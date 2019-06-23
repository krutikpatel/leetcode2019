/*
Walls And Gates
https://leetcode.com/explore/learn/card/queue-stack/231/practical-application-queue/1373/

You are given a m x n 2D grid initialized with these three possible values.

    -1 - A wall or an obstacle.
    0 - A gate.
    INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.

Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example: 

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF

After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4


*/
class Solution {
    private static final int EMPTY = Integer.MAX_VALUE;
    
    public void wallsAndGates(int[][] rooms) {
        if(rooms == null || rooms.length == 0)
            return;
        
        int r = rooms.length;
        int c = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(rooms[i][j] == 0){//gather all starting points - 0
                    q.add(new int[]{i,j});
                }
            }
        }
        
        while(!q.isEmpty()){
            int[] point = q.remove();
            int row = point[0];
            int col = point[1];
            
            //now BFS from this point.
            //go in each direction and update values and add to queue, because they also become route for next points
            
            //
            int sofar = rooms[row][col];
            
            int i = row-1;
            int j = col;
            
            if(i>=0 && rooms[i][j] == EMPTY ){
                rooms[i][j] = sofar+1;
                q.add(new int[]{i,j});
            }
            
            i = row+1;
            j = col;            
            if(i<r && rooms[i][j] == EMPTY ){
                rooms[i][j] = sofar+1;
                q.add(new int[]{i,j});
            }
            
            i = row;
            j = col-1;            
            if(j>=0 && rooms[i][j] == EMPTY ){
                rooms[i][j] = sofar+1;
                q.add(new int[]{i,j});
            }
            
            i = row;
            j = col+1;            
            if(j<c && rooms[i][j] == EMPTY ){
                rooms[i][j] = sofar+1;
                q.add(new int[]{i,j});
            }
            
            //System.out.println(sofar);
        }
        
    }
}
