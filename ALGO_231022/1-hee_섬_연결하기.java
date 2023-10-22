import java.util.*;

class Solution {
    
    /*
        최소신장트리(MST) 알고리즘을 통한 풀이
        간선의 개수가 많을 수 있어서 프림 알고리즘으로 풀음
        근데 테스트케이스를 보니 크루스칼도 통과 가능할 수도...    
    */
    
     static class Node {
        int vertex, weight; // 정점 번호, 간선의 비용
        Node next; // 인접 간선
        
        public Node(int vertex, int weight, Node next){
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }        
    }
    
    private static final int MAX = 987654321;
    
    public int solution(int n, int[][] costs) {        
        
        Node[] nodeList = new Node[n];
        int E = costs.length;
        
        for(int i = 0 ; i < E ; i ++){
            int from = costs[i][0];
            int to = costs[i][1];
            int weight = costs[i][2];
            
            nodeList[from] = new Node(to, weight, nodeList[from]);
            nodeList[to] = new Node(from, weight, nodeList[to]);
            
        }
        
        int[] cost = new int[n];
        boolean[] isVisited = new boolean[n];
        Arrays.fill(cost, MAX);
        cost[0] = 0;
        
        int answer = 0;
        
        int min, minVertex;
        for(int i = 0 ; i < n ; i ++){
            min = MAX;
            minVertex = -1;
            for(int j = 0 ; j < n ; j++){
                if(!isVisited[j] && cost[j] < min){
                    min = cost[j];
                    minVertex = j;
                }
            }
            
            if(minVertex == -1) continue;
            isVisited[minVertex] = true;
            answer += min;
            
            for(Node temp = nodeList[minVertex] ; temp != null ; temp = temp.next){
                if(!isVisited[temp.vertex] && cost[temp.vertex] > temp.weight){
                    cost[temp.vertex] = temp.weight;
                    
                }
            }
        }
        
        return answer;
    }       
}

/*
크루스칼 알고리즘을 이용한 풀이
*/

/*
import java.util.*;

class Solution {
       	
    class Edge implements Comparable<Edge>{
        int from, to, weight;
        public Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o){
            return this.weight - o.weight;
        }
    }
    
    // union-find
    static int[] parents;
    static int V, E;
    static Edge[] edgeList;
    static void make(int v){
        parents = new int[v];
        for(int i = 0 ; i < V ; i ++){
            parents[i] = i;
        }
    }
    
    static int find(int a){
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
    
    
    
    public int solution(int n, int[][] costs) {        
                
        this.V = n;
        this.E = costs.length;
		edgeList = new Edge[E];
		
		for(int i = 0 ; i < E ; i ++) {
			int from = costs[i][0];
			int to = costs[i][1];
			int weight = costs[i][2];
			edgeList[i] = new Edge(from ,to, weight);
		}
		
		make(n);
		Arrays.sort(edgeList);
        
        int answer = 0;
        int cnt = 0;
		
        for(Edge e : edgeList){
            if(union(e.from, e.to)){
                answer += e.weight;
                if(++cnt == V-1) break;
            }
        }
              
        return answer;
    }       
}
*/