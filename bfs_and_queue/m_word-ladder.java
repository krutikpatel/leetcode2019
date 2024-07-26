/*
127. Word Ladder
DescriptionHintsSubmissionsDiscussSolution

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

    Only one letter can be changed at a time.
    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.

Note:

    Return 0 if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    You may assume no duplicates in the word list.
    You may assume beginWord and endWord are non-empty and are not the same.

Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

*/
class Solution {
/*  
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(endWord);  //imp
        
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int level = 0;
        
        while(!q.isEmpty()) {
            int qsize = q.size();
            
            //process each neighbor at this level
            for(int i = 0;i<qsize;i++) {
                String curr = q.remove();
                
                if(curr.equals(endWord)) {
                    return level+1;
                }
                //take each char one by one and replace with a-z and add in queue as next level neighbors
                for(int j = 0;j<curr.length();j++) {
                    char[] currArr = curr.toCharArray();
                    for(char x = 'a';x<='z';x++) {
                        
                        currArr[j] = x;
                        String newWord = new String(currArr);
                        if(!newWord.equals(curr) && wordList.contains(newWord)){    //imp
                            
                            System.out.println(newWord);
                            q.offer(newWord);
                            wordList.remove(newWord);   //imp so that we dont revisit same word
                        }
                            
                    }
                }
            }
            level++;
        }
        
        return 0;
    }
*/
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) 
            return 0;
        
        HashSet<String> set = new HashSet<String>(wordList);
        Queue<String> q = new LinkedList<String>();
        int length = 0;
        set.add(endWord);
        q.add(beginWord);
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String w = q.poll();
                
                if (w.equals(endWord)) 
                    return length + 1;
                
                wordMatch(w, set, q);
            }
            length++;
        }
        return 0;
    }
    
    /*
    Two choices :
    -char position in source string (n chars) to replace
    -a-z as replacement
    */
    public void wordMatch(String w, Set<String> set, Queue<String> q) {
        
        //try each char of curr word
        for (int i = 0; i < w.length(); i++) {
            //work on array, easy that way
            char[] word = w.toCharArray();
            
            //try a-z for each position
            for (char c = 'a'; c <= 'z'; c++) {
                
                if (word[i] == c) 
                    continue;
                
                word[i] = c;
                String s = String.valueOf(word);
                if (set.contains(s)) {
                    set.remove(s);//remove from available words
                    q.offer(s);
                }
            }
        }
    }    
    
}
