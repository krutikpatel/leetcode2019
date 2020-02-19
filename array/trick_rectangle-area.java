/*
223. Rectangle Area
DescriptionHintsSubmissionsDiscussSolution

Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area

Example:

Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
Output: 45

Note:

Assume that the total area is never beyond the maximum possible value of int.

*/
class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        
        int areaOfSqrA = (C-A) * (D-B);
        int areaOfSqrB = (G-E) * (H-F);
        
        //important - see visual for logic
        int left = Math.max(A, E);  //max of both lefts
        int right = Math.min(G, C); //min of both rights
        int bottom = Math.max(F, B);//max of both bottoms
        int top = Math.min(D, H);   //min of both tops
        
        //If overlap - important
        int overlap = 0;
        if(right > left && top > bottom)
             overlap = (right - left) * (top - bottom);
        
        return areaOfSqrA + areaOfSqrB - overlap;
    }
}
