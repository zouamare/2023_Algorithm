import java.util.*;

class Solution {
    
    public int solution(int[] arrows) {        
        int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
        HashSet<String> set = new HashSet<>();
        HashSet<String> check = new HashSet<>();
        int x = 0, y = 0;
        int answer = 0;
        set.add("0,0");

        for(int i=0; i<arrows.length; i++){
            for(int j=0; j<2; j++){
                int dx = x+dr[arrows[i]];
                int dy = y+dc[arrows[i]];
                
                String pre = String.valueOf(x) + "," + String.valueOf(y);
                String cur = String.valueOf(dx) + "," + String.valueOf(dy);
                if(set.contains(cur) && !check.contains(pre+","+cur)) answer++;
                else set.add(cur);
                check.add(pre+","+cur);
                check.add(cur+","+pre);
                x = dx;
                y = dy;
            }
        }        
        return answer;
    }
    
}