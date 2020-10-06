class Solution {
    private int testindex = 0;
    private String[] temptest = new String[26];
    private boolean check(String test){
        for(int i = 0; i< testindex;i++){
            if(temptest[i].equals(test)){
                temptest = new String[26];
                testindex = 0;
                return false;
            }
        }
        temptest[testindex] = test;
        testindex++;
        return true;
    }
    public int lengthOfLongestSubstring(String s) {
        String[] arrayofstr = s.split("");
        int counter = 0;
        int max = 0;
        for(int i = 0;i<s.length();i++){
            if(check(arrayofstr[i])){
                counter++;
            }
            else{
                if(counter > max){
                    max = counter;
                }
                counter = 0;
                check(arrayofstr[i]);
                counter++;
            }
        }
        //System.out.println(max);
        return max;
    }
}
public class Longest_Substring_Without_Repeating_Characters {
    public static void main(String[] args){
        Solution a = new Solution();
        System.out.println("Length of longest substriing is: "+a.lengthOfLongestSubstring("pwwkew"));
    }
    
}
