/*
767. Reorganize String
DescriptionHintsSubmissionsDiscussSolution

Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"

Example 2:

Input: S = "aaab"
Output: ""

*/
/*
intuition:
we have to sort letter by count, highest first.
then pick one and nextcount char, interleave
*/
/*
Algo:
->map chars by count.
		->store int[charASCII, count] in pq -> max heap
		->now keep polling pq and interleave.
			->poll one, append in stringBuilder, reduce its count 
			->that is now prev
			-> use prev to keep track of char we just used in result in last iteration. -> because we need to add it back to pq if count >0
			aab 
				1->remove a from pq 
				2->next time remove b, 
				-> prev is a. put back in pq.
				->add b in result -> ab -> then prev becomes b 
				3->prev = b but count == 0, so dont add in pq.
					->add a to result -> aba 
			->so, if (pq is empty && prev count > 0	) -> eg: aaab
				->then we can find elems/char to separate all "a". so return false 
				
==>stop/ret false logic can be simplified i think: only add prev to pq if its cout is >0
-after loop exits see if prev != null & prev[1]>0 => ret false
                
*/
class Solution {
    public String reorganizeString(String S) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        int[] m = new int[26];
        for(int i = 0; i < S.length(); i++) m[S.charAt(i) - 'a']++; // map of char counts

        // Greedy: fetch char of max count as next char in the result.
        // Use PriorityQueue to store pairs of (char, count) and sort by count DESC. - Max-heap
        for(int i = 0; i < 26; i++) {
            if(m[i] != 0) {
                q.offer(new int[] {i, m[i]}); // add char counts to priority queue
            }
        }

        /*
        we keep track of prev used char, and add to PQ after fetching curr
        then update prev to curr.
        basically we keep alternating top 2 elems in PQ until PQ is empty
        
        if Q is empty ans we still have some char left in prev, no solution possible
        */
        int[] prev = new int[] {-1,0};
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            //fetch curr
            int[] cur = q.poll();
            
            //offer prev if valid
            if(prev[1] > 0) 
	            q.offer(prev); // add back last used character

	    //update ret
            sb.append((char)(cur[0] + 'a')); // append current character
            cur[1]--; // decrease count of current char since it's used

	    //reset prev
            prev = cur; // set this character as previous used

	    //check invalid case - q empty and we still have char left
            if(q.isEmpty() && prev[1] > 0) 
	        return ""; // if we left with anything return ""
        }
        return sb.toString();
    }
}
