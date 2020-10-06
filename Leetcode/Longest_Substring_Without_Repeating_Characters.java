class Solution {
    private int testindex = 0;
    private int recheckindex;
    private String[] temptest = new String[26];
    private boolean check(String test){
        for(int i = 0; i< testindex;i++){
            System.out.println(temptest[i]);
            if(temptest[i].equals(test)){
                System.out.println("existed");
                temptest = new String[26];
                recheckindex = testindex;
                testindex = 0;
                return false;
            }
        }
        temptest[testindex] = test;
        testindex++;
        System.out.println("no match");
        return true;
    }
    public int lengthOfLongestSubstring(String s) {
        String[] arrayofstr = s.split("");
        /*
        for(String ss: arrayofstr){
            System.out.println(ss);
        }
        */
        int counter = 0;
        int max = 0;
        for(int i = 0;i<s.length();i++){
            System.out.println("test input: "+arrayofstr[i]);
            if(check(arrayofstr[i])){
                counter++;
                if(counter > max){
                    max = counter;
                }
            }
            else{
                if(counter > max){
                    max = counter;
                }
                counter = 0;
                //System.out.println("Recheck: "+ arrayofstr[i]);
                for(int j = 0;j<recheckindex;j++){
                    System.out.println("Recheck: "+ arrayofstr[i-j]);
                    if(check(arrayofstr[i-j])){
                        counter++;
                    }
                    else{
                        counter = 0;
                        for(int k = (i-recheckindex+1);k<=i;k++){
                        System.out.println("Recheck: "+ arrayofstr[k]);
                        check(arrayofstr[k]);
                        counter++;    
                        }
                        //System.out.println("Recheck: "+ arrayofstr[i]);
                        //check(arrayofstr[i]);
                        //counter++;
                    }
                }
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
