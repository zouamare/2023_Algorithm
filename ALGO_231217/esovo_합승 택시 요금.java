import java.util.*;

class Solution {
    
    class Node implements Comparable<Node>{
        int no, fare;
        
        Node(int no, int fare){
            this.no = no;
            this.fare = fare;
        }
        
        public int compareTo(Node n){
            return this.fare-n.fare;
        }
    }
    
    static int[][] dist;
    static List<Node>[] adj;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        adj = new ArrayList[n];
        for(int i=0; i<n; i++) adj[i] = new ArrayList<>();
        for(int i=0; i<fares.length; i++){
            adj[fares[i][0]-1].add(new Node(fares[i][1]-1, fares[i][2]));
            adj[fares[i][1]-1].add(new Node(fares[i][0]-1, fares[i][2]));
        }
        
        // Dijkstra 알고리즘 적용
        dist = new int[3][n];
        for(int i=0; i<3; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        dijkstra(s-1, 0, n); // S -> N
        dijkstra(a-1, 1, n); // A -> N
        dijkstra(b-1, 2, n); // B -> N
        
        // (S -> N) + (A -> N) + (B -> N) 중 최소인 값 반환
        int answer = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) answer = Math.min(answer, dist[0][i]+dist[1][i]+dist[2][i]);
        return answer;
    }
    
    private void dijkstra(int start, int idx, int n){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[idx][start] = 0;
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            for(Node next : adj[cur.no]){
                if(dist[idx][next.no] > cur.fare+next.fare){
                    dist[idx][next.no] = cur.fare+next.fare;
                    pq.offer(new Node(next.no, dist[idx][next.no]));
                }
            }
        }
    }
}