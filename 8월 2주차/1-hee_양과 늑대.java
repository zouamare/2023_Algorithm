package com.java.week17;

import java.util.*;

public class Solution1 {
    
//     private static class Node implements Comparable<Node>{
//         int no;
//         int type;
//         Node left, right;
//         public Node(int no, int type){
//             this.no = no;
//             this.type = type;
//         }        
//         @Override
//         public int compareTo(Node o){
//             return this.no - o.no;            
//         }
//         @Override
//         public String toString(){
//             return "Node = [ no = "+this.no+", type = "+this.type+" ]";
//         }
//     }
    
    private static class Edge{
        int from, to;
        public Edge(int from, int to){
            this.from = from;
            this.to = to;
        }
        @Override
        public String toString(){
            return "Edge = [ from = "+this.from+", to = "+this.to+" ]";
        }        
    }
    
    
    
    public int solution(int[] info, int[][] edges) {
      
        // 간선에 대한 정보를 담을 배열
        // why? 객체로 변환해서 프로퍼티로 참조해서 가독성 있게 쓰려고~
        Edge[] edgeList = new Edge[edges.length];
        
        // Node 기준이 아닌, Edge를 기준으로 입력 값을 받아준다.
        for(int i = 0 ; i < edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];                        
            edgeList[i] = new Edge(from, to);         
        }             
        
        // 노드의 수만큼 방문체크 배열을 init 해줌.
        // 초기 노드는 0번이므로, 첫번째 노드에 대해 방문처리
        isVisited = new boolean[info.length];
        isVisited[0] = true;        
        max = 0; 
        edgeSearch(1,0, edgeList, info); // dfs 시작
        return max; // 최대값 리턴!
    }
    
    // 이진트리의 모든 노드를 방문하게 하는 완전 탐색 dfs    
    static boolean[] isVisited;
    static int max;
    private static void edgeSearch(
        int sheep, int wolf, // 현재 재귀 함수에서의 양과 늑대의 카운트
        Edge[] edgeList, // 간선 정보
        int[] node // 노드 번호별 타입이 담긴 배열, index = 노드번호, value = 타입(0 or 1)
    ){
        if(sheep > wolf){
            max = Math.max(sheep, max);
        }else return; // 재귀를 break 하는 역할!, 늑대 수가 양보다 많아져 잡아먹히는 것을 방지!
        
        // 중단된 재귀를 다시 살리는 역할!
        for(Edge e : edgeList){
            if(isVisited[e.from] && !isVisited[e.to]){ // 부모는 방문했는데, 자식 노드를 방문해야하는 경우에 대하여!
                isVisited[e.to] = true;                
                if(node[e.to] == 0){
                    edgeSearch(sheep+1, wolf, edgeList, node);
                }else {
                    edgeSearch(sheep, wolf+1, edgeList, node);
                }
                isVisited[e.to] = false;
            }
        }        
    } 
}