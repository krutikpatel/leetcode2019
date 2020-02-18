/*
93. Restore/Enumerate IP Addresses
DescriptionHintsSubmissionsDiscussSolution

Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]

*/
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ret = new ArrayList<>();
        //helper("",s,0,0,ret);
        restoreIp(s,ret,0,"",0);
        return ret;
    }
    /*
    private void helper(String sofar, String s, int dots, int begin, List<String> ret) {
        System.out.println(sofar);
        if(sofar.length() == s.length()+3) {
            ret.add(sofar);
            return;
        }
                
        //choices : where we put dots
        //3 dots
        for(int i=dots;i<3;i++) {
            for(int j=begin;j<s.length();j++) {
                //if valid proceed
                String curr = s.substring(begin,j);
                if(isValid(curr)) {                    
                    helper(sofar+curr+".", s, i+1, begin+curr.length(),ret); 
                }
                //backtrack
            }
        }
    }
    */
    /*
    considering choices as each seg count helps
        rather than counting dots and 
    -still need to track begin index 
    */
    private void restoreIp(String given, List<String> ret, int begin_idx, String sofar, int seg_count) {
        if (seg_count > 4) 
            return;
        if (seg_count == 4 && begin_idx == given.length()) 
            ret.add(sofar);

        //choices: try to get one segment at a time, with 1 to 3 digits
        for (int i=1; i<4; i++) {   //i represents length of segment/substring
            if (begin_idx+i > given.length()) 
                break;
            String s = given.substring(begin_idx,begin_idx+i);
            if(!isValid(s))
                continue;
            
            /* Imp DO NOT MODIFY UT=R LOCAL COPY OF SOFAR, JUST PASS MODIFIED STRING VIA ARG TO CALL, OR CREATE NEW VAR TO PASS
            sofar = sofar+s;
            if(seg_count != 3)
                sofar = sofar+".";            
            restoreIp(given, ret, begin_idx+i, sofar, seg_count+1);//no dot for last segment
            */
            /* Or do this, create local var and pass that to call
            String next = sofar+s;
            if(seg_count != 3)
                next = next+".";            
            restoreIp(given, ret, begin_idx+i, next, seg_count+1);//no dot for last segment
            */
            restoreIp(given, ret, begin_idx+i, sofar+s+(seg_count==3?"" : "."), seg_count+1);//no dot for last segment
            
        }
    }
    //
    public boolean isValid(String s){
        if(s.length()>3 || s.length()==0 || (s.charAt(0)=='0' && s.length()>1) || Integer.parseInt(s)>255)
            return false;
        return true;
    }
    
}
