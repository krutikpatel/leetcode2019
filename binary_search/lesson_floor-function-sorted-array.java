/*
Floor function:
floor(x) :returns closest value, <x.

https://www.geeksforgeeks.org/floor-in-a-sorted-array/

*/
/*
linear slower way
*/
/* An inefficient function to get index of floor  
of x in arr[0..n-1] 
*/
static int floorSearch(int arr[], int n, int x)  
{  
    // corner : If last element is smaller than x  
    if (x >= arr[n-1])  
        return n-1;  
  
    // corner: If first element is greater than x  
    if (x < arr[0])  
        return -1;  
  
    // Linearly search for the first element  
    // greater than x  
    //start with index=1
    for (int i=1; i<n; i++)  
    if (arr[i] > x)  
        return (i-1);  
  
    return -1;  
}  

/*
Binary search way
*/
static int floorBianrySearch(int arr[], int n, int x) {
    int l = 1;//begin with 1, like we do for linear search, because we are lookign for elem smaller
    int r = n-1;
    
    while(l<r) {
        int mid = l+(r-l)/2;
        if(arr[mid] == x)
            return mid;
        else if(x < arr[mid]) {
            r = mid;
        } else {
            //arr[mid] < x
            l = mid+1;
        }
    }
    //by this time l will be just one above x
    return l-1;
}
