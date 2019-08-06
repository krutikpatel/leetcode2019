/*
Combinations
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/
/*
once we found one pair, we need to backtrack, remove all elems one by one to find other solutions.
*/
class Solution {

    public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> combs = new ArrayList<List<Integer>>();
		combine(combs, new ArrayList<Integer>(), 1, n, k);
		return combs;
	}
    
    //our choices:
    //k nums in list
    //each nums 1 to 4 (non repetetive in any order)
	public static void combine(List<List<Integer>> combs, List<Integer> sofar, int start, int n, int k) {
		//maintaining k helps to reduce k's count loop from this method.
		if(sofar.size()==k) {
			combs.add(new ArrayList<Integer>(sofar));
			return;
		}

		//how to makre sure 1,2 and 2,1 both dont get added ? - clever choice of index/nums remaining
		//at any given point, our remaining choices already skips 1 to start-1 digits. Choices reduce as we move down the nums
		//so once 1,2, 1,3, 1,4 are added
		//that inherently avoids : 2,1 because 2's choices are 3 and 4 only
		//3's choices only 4
		//4's choices - none other
		for(int i=start;i<=n;i++) {
				//curr choice
		    sofar.add(i);

		    //recuraively make next choice(s)
		    combine(combs, sofar, i+1, n, k);

		    //backtrack. Not only helps undo wrong elems, also helps figure out all other possible solutions.
		    //on some points we remove both nums from list and list becomes empty and we try new first num
				sofar.remove(sofar.size()-1);
		}
	}
}
