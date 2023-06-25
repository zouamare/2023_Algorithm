import java.util.*;
public class P3 {

    static class Edge implements Comparable<Edge>{
        int index;
        int cost;

        Edge (int index, int cost){
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e){
            return this.cost - e.cost;
        }
    }

    static final int MAX = 20000001; // 200 * 100000 + 1
    static ArrayList<ArrayList<Edge>> graph;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        graph = new ArrayList<>();
        for(int i = 0 ; i <= n ; i ++){
            graph.add(new ArrayList<>());
        }

        for(int[] i : fares){
            graph.get(i[0]).add(new Edge(i[1], i[2]));
            graph.get(i[1]).add(new Edge(i[0], i[2]));
        }

        int[] startA = new int[n + 1];
        int[] startB = new int[n + 1];
        int[] start = new int[n + 1];

        Arrays.fill(startA, MAX);
        Arrays.fill(startB, MAX);
        Arrays.fill(start, MAX);

        startA = dijkstra(a, startA);
        startB = dijkstra(b, startB);
        start = dijkstra(s, start);

        for(int i = 1; i <= n ; i ++) answer = Math.min(answer, startA[i] + startB[i] + start[i]);
        return answer;
    }

    static int[] dijkstra (int start, int[] costs) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        costs[start] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            int nIndex = now.index;
            int nCost = now.cost;

            if(nCost > costs[nIndex]) continue;

            ArrayList<Edge> edges = graph.get(nIndex);
            for (Edge edge : edges) {
                int cost = costs[nIndex] + edge.cost;

                if (cost < costs[edge.index]) {
                    costs[edge.index] = cost;
                    pq.offer(new Edge(edge.index, cost));
                }
            }
        }
        return costs;
    }

    public static void main(String[] args) {
        P3 p = new P3();
        System.out.println(p.solution(
                6,
                4,
                6,
                2,
                new int[][] {
                        new int[] {4, 1, 10},
                        new int[] {3, 5, 24},
                        new int[] {5, 6, 2},
                        new int[] {3, 1, 41},
                        new int[] {5, 1, 24},
                        new int[] {4, 6, 50},
                        new int[] {2, 4, 66},
                        new int[] {2, 3, 22},
                        new int[] {1, 6, 25}
                }));
        System.out.println(p.solution(
                7,
                3,
                4,
                1,
                new int[][] {
                        new int[] {5, 7, 9},
                        new int[] {4, 6, 4},
                        new int[] {3, 6, 1},
                        new int[] {3, 2, 3},
                        new int[] {2, 1, 6}
                }));
        System.out.println(p.solution(
                6,
                4,
                5,
                6,
                new int[][] {
                        new int[] {2, 6, 6},
                        new int[] {6, 3, 7},
                        new int[] {4, 6, 7},
                        new int[] {6, 5, 11},
                        new int[] {2, 5, 12},
                        new int[] {5, 3, 20},
                        new int[] {2, 4, 8},
                        new int[] {4, 3, 9}
                }
        ));
    }
}
