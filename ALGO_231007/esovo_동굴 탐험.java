import java.util.*;

class Solution {

    public boolean solution(int n, int[][] path, int[][] order) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] before = new int[n];
        int[] after = new int[n];
        for (int i=0; i<n; i++) graph.add(new ArrayList<>());
        for (int i=0; i<path.length; i++) {
            graph.get(path[i][0]).add(path[i][1]);
            graph.get(path[i][1]).add(path[i][0]);
        }
        
        for (int i=0; i<order.length; i++) {
            before[order[i][1]] = order[i][0];
            after[order[i][0]] = order[i][1];
        }
        
        if(before[0] != 0) return false;
        
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[n];
        q.offer(0);
        visited[0] = 2;
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            cnt++;
            for (int next : graph.get(cur)) {
                // 이미 방문한 경우
                if (visited[next] == 2) {
                    continue;
                }
                // 나중에 방문할 방을 방문한 경우
                if (visited[before[next]] != 2) { 
                    visited[next] = 1;
                    continue;
                }
                q.offer(next);
                visited[next] = 2;
            }
            
            // 나중에 방문할 방을 방문할 수 있는지 확인
            int temp = after[cur];
            if (temp != 0 && visited[temp] == 1) {
                q.offer(temp);
                visited[temp] = 2;
            }
        }
        return cnt == n;
    }

}