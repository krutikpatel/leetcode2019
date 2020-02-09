/*
490. The Maze
DescriptionHintsSubmissionsDiscussSolution

There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

 

Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true

Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
*/
class Solution {
    /*
    lesson
    NOTE:
    we do DFS, BUT NOT at every cell. we try to roll in each direction as much as we can. 
        -normal DFS with 1 cell movement each time is  Bruteforce DFS. and gives TLE and memory limit exceeded as well
    ==
    lesson: BFS from start to end is shorted distance route between them
    if queue ended and we did not encounter end, there is no route
    
    */
    
    private static final int[][] DIRECTIONS = { {0,1},{0,-1},{1,0},{-1,0}};
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, visited, start, destination);
    }

    
    private boolean dfs(int[][] maze, boolean[][] visited, int[] start, int[] destination) {
        if (visited[start[0]][start[1]]) return false;
        if (Arrays.equals(start, destination)) return true;
        
        visited[start[0]][start[1]] = true;
        
        //try all 4 direction
        for (int i = 0; i < DIRECTIONS.length; i++) {
            //imp roll in each direction as much as we can before changing direction and next DFS
            int[] newStart = roll(maze, start[0] , start[1] , DIRECTIONS[i]);
            
            if (dfs(maze, visited, newStart, destination)) 
                return true;
        }
        
        return false;
    }
    
    /* normal bruteforce DFS 
    private boolean dfs(int[][] maze, boolean[][] visited, int[] start, int[] destination) {
        if(!canRoll(maze, start[0], start[1]))
            return false;
        
        if (Arrays.equals(start, destination)) return true;
        
        visited[start[0]][start[1]] = true;
        
        //try all 4 direction
        boolean ret = false;
        ret = ret || dfs(maze, visited, new int[]{start[0]+1,start[1]}, destination);
        ret = ret || dfs(maze, visited, new int[]{start[0]-1,start[1]}, destination);
        ret = ret || dfs(maze, visited, new int[]{start[0],start[1]+1}, destination);
        ret = ret || dfs(maze, visited, new int[]{start[0],start[1]-1}, destination);
        
        return ret;
    }
    */
    
    private int[] roll(int[][] maze, int row, int col, int[] direction) {
        while (canRoll(maze, row + direction[0] , col + direction[1])) {
            row += direction[0];
            col += direction[1];
        }
        
        return new int[]{row, col};
    }
    
    private boolean canRoll(int[][] maze, int row, int col) {
        if (row >= maze.length || row < 0 || col >= maze[0].length || col < 0) return false;
        return maze[row][col] != 1; // 1 is a wall
    }
}
