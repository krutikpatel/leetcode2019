/*
Lesson : it is just shortest distance between 2 points problem

505. The Maze II
DescriptionHintsSubmissionsDiscussSolution

There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

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

Output: 12

Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
*/
/*
We need to explore ALL the paths and find the shortest distance, but we can prune paths where the distance to reach a square is more than the currently shortest distance.
Because of that, we can use either BFS or DFS since it doesnt matter as we need to explore ALL the paths.. The main difference is when each path will be pruned. In the average case, BFS would be a much better result cause more paths will be pruned since the first few moves to a particular square will generally yield the shortest distance.
We use a distance[][] array that keeps track of the minimum distance to reach that square..
And prune any visits to that square if the distance is more than the minimum distance.
*/
/*
Algo:
-we will try to reach dest with all various ways using BFS
-and use int[][] distance to store shortest dist to reach that point. for dest we will update with min dist everyt time we reach dest
*/
public class Solution {
    int[][] steps = new int[][]{{-1,0}, {1, 0}, {0, -1}, {0, 1}}; //up down left right
    
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        
        //keep storing min distance, everytime we touch the particular cell
        int[][] distanceMemo = new int[m][n];  //can also use a hashmap
        for(int i = 0; i < m; i++) {
            Arrays.fill(distanceMemo[i], Integer.MAX_VALUE);
        }
        
        Queue<int[]> queue = new LinkedList();
        distanceMemo[start[0]][start[1]] = 0;
        queue.add(start);
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            //try all 4 dirs
            for (int i=0; i<4; i++) {
                //roll and get new pos
                int[] newPos = move(i, curr[0], curr[1], maze);
                
                //currCell + newCell
                int totalDistance = distanceMemo[curr[0]][curr[1]] + newPos[2];
                
                if (totalDistance < distanceMemo[newPos[0]][newPos[1]]) {
                    distanceMemo[newPos[0]][newPos[1]]  =  totalDistance;
                    
                    //if newpos is dest, dont add to queue
                    if (newPos[0] == destination[0] && newPos[1] == destination[1]) { 
                        continue;
                    }
                    queue.add(newPos);
                }
            }
        }
        int shortest_distance = distanceMemo[destination[0]][destination[1]];
        return shortest_distance == Integer.MAX_VALUE ? -1 : shortest_distance; 
    }
    
    //3rd int in return arr is disance
    public int[] move(int dir,  int x, int y, int[][] maze) {
        int[] pos = new int[]{x, y, 0};
        while (isValid(maze, pos[0] + steps[dir][0] , pos[1] +  steps[dir][1])) {
            pos[0] += steps[dir][0];
            pos[1] += steps[dir][1];
            pos[2] += 1;
        }
        
        return pos;
    }
    
    
    public boolean isValid(int[][] maze, int x, int y) {
        if (!(x>=0 && y >=0 && x < maze.length && y < maze[0].length)) { 
            return false; 
        }
        return maze[x][y] != 1; //not a wall
    }
    
}
