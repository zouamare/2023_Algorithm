import java.util.*;

class Solution {
    public int solution(String word) {
        int[] up = {781, 156, 31, 6, 1};
        int ans = 0;
        Map<Character, Integer> dict = new HashMap<>(){{
            put('A', 0);
            put('E', 1);
            put('I', 2);
            put('O', 3);
            put('U', 4);
        }};
        for (int i = 0; i < word.length(); ++i) {
            ans += up[i] * dict.get(word.charAt(i));
        }
        return ans + word.length();
    }
}