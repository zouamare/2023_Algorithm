import java.util.*;

class Solution {

    static int[] p;
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));

        p = new int[n];
        for(int i=0; i<n; i++) p[i] = i;

        int answer = 0;
        int count = 0;
        for(int i=0; i<costs.length; i++) {
            int start = costs[i][0];
            int end = costs[i][1];
            int cost = costs[i][2];

            if(find(start) != find(end)) {
                union(start, end);
                answer += cost;
                count++;
            }

            if(count == n-1) break;
        }

        return answer;
    }

    private int find(int a) {
        if (p[a] == a) return a;
        return p[a] = find(p[a]);
    }

    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        p[b] = a;
    }
    
}