/*
You are given an 2-D array points where points[i] = [xi, yi] represents the coordinates of a point on an X-Y axis plane. You are also given an integer k.

Return the k closest points to the origin (0, 0).

The distance between two points is defined as the Euclidean distance (sqrt((x1 - x2)^2 + (y1 - y2)^2)).

You may return the answer in any order.

*/
 class Solution {
    public int[][] kClosest(int[][] points, int k) {

        //Need max heap, since we remove from the top upon overflow
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> getDistance(b)-getDistance(a));
        for(int[] p: points) {
            maxHeap.offer(p);
            if(maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] ret = new int[k][2];
        for(int i=0;i<k;i++){
            ret[i] = maxHeap.poll();
        }
        return ret;
    }

    private int getDistance(int[] a) {
        return (a[0]*a[0]) + (a[1] * a[1]);
    }
}
