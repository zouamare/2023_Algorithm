package coding_test.AlgoY2023.M10.D22;

import java.util.*;

public class 섬연결하기 {
    int[] p;

    public int solution(int n, int[][] costs) {
        PriorityQueue<Data> q = new PriorityQueue<>(new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.cost - o2.cost;
            }
        });
        p = new int[n];

        for(int i = 0; i < n; i++){
            p[i] = i;
        }

        for(int i = 0; i < costs.length; i++){
            q.offer(new Data(costs[i][0], costs[i][1], costs[i][2]));
        }

        int cnt = 1;
        int minCost = 0;
        while(!q.isEmpty() && cnt < n){
            Data now = q.poll();

            if(unionFind(now.start, now.end)){
                minCost += now.cost;
                cnt++;
            }
        }

        return minCost;
    }

    private boolean unionFind(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y){
            return false;
        }
        p[y] = x;
        return true;
    }

    private int find(int x){
        if(x == p[x]){
            return x;
        }
        return p[x] = find(p[x]);
    }

    class Data{
        int start;
        int end;
        int cost;

        public Data(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
