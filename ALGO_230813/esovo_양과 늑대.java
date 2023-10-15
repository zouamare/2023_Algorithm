import java.util.*;

/*
    이미 지나갔어도 다시 지나갈 수 있음
    이 때 양이나 늑대 수를 다시 세면 안됨..
*/

class Solution {
    
    class Node{
        int num, ani;
        Node(int num, int ani){
            this.num = num;
            this.ani = ani;
        }
    }
    
    static int answer;
    static int[] counted;
    static boolean[][][] visited;
    static List<Node>[] adj_list;
    
    public int solution(int[] info, int[][] edges) {        
        answer = 0;
        counted = new int[info.length];
        // info.length = 12일때 양이 12마리이면 인덱스 넘어가기 때문에 +1 처리
        visited = new boolean[info.length][info.length+1][info.length+1];
        adj_list = new ArrayList[info.length];
        
        for(int i=0; i<info.length; i++) counted[i] = info[i];
        for(int i=0; i<info.length; i++) adj_list[i] = new ArrayList<>();
        for(int i=0; i<edges.length; i++){
            adj_list[edges[i][0]].add(new Node(edges[i][1], info[edges[i][1]]));
            adj_list[edges[i][1]].add(new Node(edges[i][0], info[edges[i][0]]));
        }
        
        visited[0][0][0] = true;
        dfs(0, 0, 0);
        
        return answer;
    }
    
    private void dfs(int sheep, int wolf, int order){
        int temp = -1;
        if(counted[order] != -1){
            if(counted[order] == 0){
                temp = 0;
                sheep++;
            }
            else{
                temp = 1;
                wolf++;
            }
        }
        
        if(sheep > wolf) answer = Math.max(answer, sheep);
        else return;
        
        for(int i=0; i<adj_list[order].size(); i++){
            int num = adj_list[order].get(i).num;
            int ani = adj_list[order].get(i).ani;
            
            if(!visited[num][sheep][wolf]){
                visited[num][sheep][wolf] = true;
                counted[order] = -1;
                
                dfs(sheep, wolf, num);
                
                visited[num][sheep][wolf] = false;
                counted[order] = temp;
            }
        }
    }
    
}