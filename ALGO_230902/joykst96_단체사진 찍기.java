import java.util.*;

class Solution {
    static class Condition {
        char friend1;
        char friend2;
        char condition;
        int distance;
        
        Condition(char friend1, char friend2, char condition, int distance) {
            this.friend1 = friend1;
            this.friend2 = friend2;
            this.condition = condition;
            this.distance = distance + 1;
        }
        
    }
    
    Condition[] conditions;
    int count;
    
    public int solution(int n, String[] data) {
        char[] members = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        conditions = new Condition[n];
        count = 0;
        for (int i = 0; i < n; ++i) {
            char friend1 = data[i].charAt(0);
            char friend2 = data[i].charAt(2);
            char condition = data[i].charAt(3);
            int distance = data[i].charAt(4) - '0';
            conditions[i] = new Condition(friend1, friend2, condition, distance);
        }
        dfs(members, new char[8], new boolean[8], 0);
        return count;
    }
    
    void dfs(char[] members, char[] photo, boolean[] visited, int depth) {
        if (depth == 8) {
            if(check(photo)) {
                ++count;
            }
            return;
        }
        for (int i = 0; i < 8; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                photo[depth] = members[i];
                dfs(members, photo, visited, depth + 1);
                visited[i] = false;
            }
        }
    }
    
    boolean check(char[] photo) {
        for(Condition c: conditions) {
            int f1 = -1;
            int f2 = -1;
            for (int i = 0; i < 8; ++i) {
                if (photo[i] == c.friend1) f1 = i;
                if (photo[i] == c.friend2) f2 = i;
            }
            int distance = Math.abs(f1 - f2);
            switch(c.condition) {
                case '=':
                    if (c.distance != distance) return false;
                    break;
                case '<':
                    if (c.distance <= distance) return false;
                    break;
                case '>':
                    if (c.distance >= distance) return false;
                    break;
            }
        }
        return true;
    }
}