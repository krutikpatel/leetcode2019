/* easy
Minimum Index Sum of Two Lists

Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

Example 1:

Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
Output: ["Shogun"]
Explanation: The only restaurant they both like is "Shogun".

Example 2:

Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]
Output: ["Shogun"]
Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).

*/
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        //map to save/map indices form list1
        //iterate thru list 2 and 
        //      -find common elem, 
        //      -then calculate index sum, if min, clear ret list and add this elem        
        
        if(list1 == null || list2 == null)
            return new String[0];
        
        HashMap<String,Integer> map = new HashMap<>();
        
        for(int i=0;i<list1.length;i++){
            map.put(list1[i],i);
        }
        
        int min = Integer.MAX_VALUE;
        List<String> ret = new ArrayList<>();
        
        for(int i=0;i<list2.length;i++){
            if(map.containsKey(list2[i])){
                int index_sum = map.get(list2[i]) + i;
                if(index_sum == min){
                    ret.add(list2[i]);
                }else if(index_sum < min){
                    ret.clear();
                    ret.add(list2[i]);
                    min = index_sum;
                }
            }
        }
        
        return ret.toArray(new String[ret.size()]);
    }
}
