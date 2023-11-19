import java.util.*;

class Solution {
    static int[] p;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < wires.length; ++i) {
            p = new int[n];
            for (int j = 0; j < n; ++j) p[j] = j;
            for (int j = 0; j < wires.length; ++j) {
                if (j == i) continue;
                union(wires[j][0] - 1, wires[j][1] - 1);
            }
            Map<Integer, Integer> count = new HashMap<>();
            for (int j: p) count.put(j, count.getOrDefault(j, 0) + 1);
            int a = -1;
            int b = -1;
            for (int j: count.keySet()) {
                if (a == -1) a = count.get(j);
                else b = count.get(j);
            }
            answer = Math.min(answer, Math.abs(a - b));
        }
        return answer;
    }
    
    static int find(int x) {
        if (p[x] == x) return x;
        return find(p[x]);
    }
    
    static void union(int x, int y) {
        int a = find(x);
        int b = find(y);
        if (a == b) return;
        else if (a < b) p[b] = a;
        else p[a] = b;
    }
}