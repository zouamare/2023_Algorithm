import java.util.*;

class Solution {
    
    static int answer;
    static List<Integer>[] list;
    public int solution(int n, int[][] wires) {
        list = new ArrayList[n];
        for(int i=0; i<n; i++) list[i] = new ArrayList<>();
        for(int i=0; i<wires.length; i++){
            list[wires[i][0]-1].add(wires[i][1]-1);
            list[wires[i][1]-1].add(wires[i][0]-1);
        }

        answer = 100;
        for(int i=0; i<wires.length; i++){
            bfs(wires[i][0]-1, wires[i][1]-1);    
        }

        return answer;
    }
    
    private void bfs(int start, int end){
        int count = 1;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[list.length];
        queue.add(0);
        visited[0] = true;
        
        while(!queue.isEmpty()){
            int num = queue.poll();
            for(int i=0; i<list[num].size(); i++){
                if(num==start && list[num].get(i)==end || num==end && list[num].get(i)==start) continue;
                if(visited[list[num].get(i)]) continue;
                
                visited[list[num].get(i)] = true;
                queue.add(list[num].get(i));
                count++;
            }
        }
        
        answer = Math.min(answer, Math.abs(count-(list.length-count)));
        
    }
    
}