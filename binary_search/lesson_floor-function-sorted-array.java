/*
Floor function:
floor(x) :returns closest value, <x.

https://www.geeksforgeeks.org/floor-in-a-sorted-array/

*/
/*
IMP note: TreeMap in java has map.floorEntry(key_x)
and map.floorKey()
functions readily available

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
Binary search way - template -2
notice l and r
*/
static int floorBianrySearch(int arr[], int n, int x) {
    int l = 1;//begin with 1, like we do for linear search, because we are lookign for elem smaller
    int r = n;//since we are not after particular elem
    
    while(l<r) {
        int mid = l+(r-l)/2;
        if(arr[mid] == x)
            return mid;
        else if(x > arr[mid]) {
            l = mid+1;            
        } else {
            //x < arr[mid]
            r = mid;
        }
    }
    //by this time l will be just one above x
    return l-1;
}

/*
template 1 - easier
-normal bianry search function : if val not found, it ends up at smaller closest !
*/
static int floorBianrySearch_easy(int arr[], int n, int x) {
{
    int start = 0;
        int end = array.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;    //notice it is not l+(r-l)/2
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
}

static int floorBianrySearch_easy2(int arr[], int n, int x) {
{
    int start = 0;
        int end = array.length - 1;
    int ret = 0;
        while (start <= end) {
            int mid = start +(end-start) / 2;
            
            if (array[mid] <= target) {
                ret = mid;//keep recording each time and return at end
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ret;
}
    
