import java.util.HashSet;
import java.util.Set;
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> checklist = new HashSet<Character>();
        int head = 0;
        int tail = 0;
        int max = 0;
        while(head < s.length()){
            if(!checklist.contains(s.charAt(head))){
                checklist.add(s.charAt(head));
                head++;
                if(checklist.size()>max){
                    max = checklist.size();
                }
            }
            else{
                checklist.remove(s.charAt(tail));
                tail++;
            }
        }
        return max;
    }
}

public class Longest_Substring_Without_Repeating_Characters {
    public static void main(String[] args){
        Solution a = new Solution();
        System.out.println("Length of longest substriing is: "+a.lengthOfLongestSubstring("pwwkew"));
    }
    
}
