/* easy
819. Most Common Word
DescriptionHintsSubmissionsDiscussSolution

Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.

 

Example:

Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.

Note:
The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
There are no hyphens or hyphenated words.
Words only consist of letters, never apostrophes or other punctuation symbols.
*/
class Solution {
    /*
    HashMap for count, and one pass, track max
    
    we need to skip spaces and symbols. so go char by char and use character.isLetter
    -dont use split,regex, nor complicate it with 2 pointers
    */
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> ban = new HashSet<>();
        for(String s:banned)
            ban.add(s);            
        
        HashMap<String,Integer> map = new HashMap<>();
        int max = 0;
        String ret = "";
        StringBuilder sb = new StringBuilder();
        for(char c: paragraph.toCharArray()) {
            if(Character.isLetter(c)) {
                sb.append(c);
            } else {
                //see if sb is non empty
                if(sb.length()!=0){
                    String word = sb.toString().toLowerCase();
                    if(!ban.contains(word)){
                        map.put(word,map.getOrDefault(word,0)+1);
                        
                        if(map.get(word) > max){
                            ret = word;
                            max = Math.max(max,map.get(word));
                        }                        
                    }
                    sb = new StringBuilder();
                }
            }
        }
        //if whole paragraph wasa single word
        if(sb.length()!=0){
            String word = sb.toString().toLowerCase();
            if(!ban.contains(word)){
                map.put(word,map.getOrDefault(word,0)+1);
                if(map.get(word) > max){
                    ret = word;
                    max = Math.max(max,map.get(word));
                }
            }                        
        }
        
        return ret;
    }
}
