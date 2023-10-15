import java.util.*;

class Solution {
    
    public String solution(String input_string) {
        char[] input = input_string.toCharArray();
        int[] count = new int[26];
        
        int cur = 0, next = 1;
        while(cur < input.length){
            while(true){
                if(next >= input.length) break;
                if(input[cur] != input[next]) break;
                next++;
            }
            count[input[cur]-'a']++;
            cur = next;
            next++;
        }
        
        StringBuilder answer = new StringBuilder();
        for(int i=0; i<26; i++){
            if(count[i]>=2) answer.append((char)('a' + i)); 
        }
        
        return answer.toString().equals("") ? "N" : answer.toString();
    }
    
}