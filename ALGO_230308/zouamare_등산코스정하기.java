package coding_test.AlgoY2023.M03.D08;
import java.util.*;
/*
    1) dijkstra 알고리즘을 이용한 방식
    - 처음에 단순히 bfs 알고리즘을 사용하려고 하니 시간 초과가 발생했다.
    - 따라서 탐색 알고리즘 중 시간을 줄일 수 있는 다익스트라 알고리즘을 사용했다.
    - 어차피 양방향 탐색이므로 gate를 모두 넣어두고 gate에서 특정 summit에 도달하면 멈추도록 설계했다. (두번 계산하지 않음)
    - 기존 dijkstra와는 다르게 도달하기까지의 거리가 아니라 도달하기 까지의 최대 intensity를 minDist에 저장했다.
    - minDist[next.v]가 Math.max(node.intensity, next.w)보다 클 경우에만 큐에 더하는 방식으로 접근했다.
* */
class PG_등산코스정하기 {
    public static void main(String[] args) {
        System.out.println(solution(6, new int[][] {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}}, new int[]{1, 3}, new int[]{5}));
    }

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        ArrayList<Vertex>[] graph = new ArrayList[n + 1];

        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] path : paths){
            graph[path[0]].add(new Vertex(path[1], path[2]));
            graph[path[1]].add(new Vertex(path[0], path[2]));
        }

        boolean[] isSummit = new boolean[n + 1];
        boolean[] isGate = new boolean[n + 1];

        for(int gate : gates){
            isGate[gate] = true;
        }

        for(int summit : summits){
            isSummit[summit] = true;
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        int idx = 0, minIntensity = Integer.MAX_VALUE;
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        for(int gate : gates){
            q.offer(new Node(gate, gate, 0));
            minDist[gate] = 0;
        }

        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.intensity >= minIntensity){
                continue;
            }
            if(isSummit[node.now]){
                if(node.intensity < minIntensity || (node.intensity == minIntensity && idx > node.now)){
                    minIntensity = node.intensity;
                    idx = node.now;
                }
                continue;
            }

            for(Vertex next : graph[node.now]){
                if(minDist[next.v] > Math.max(node.intensity, next.w)){
                    minDist[next.v] = Math.max(node.intensity, next.w);
                    q.offer(new Node(node.start, next.v, Math.max(node.intensity, next.w)));
                }
            }
        }

        int[] answer = {idx, minIntensity};
        return answer;
    }

    static class Vertex{
        int v, w;   // v: 정점, w: 가중치

        public Vertex(int v, int w){
            this.v = v;
            this.w = w;
        }
    }

    static class Node implements Comparable<Node>{
        int start, now, intensity;

        public Node(int start, int now, int intensity){
            this.start = start;
            this.now = now;
            this.intensity = intensity;
        }

        public int compareTo(Node n){
             return this.intensity - n.intensity;
        }
    }
}