import java.util.*;

class Solution {
    
    static class Process{
        int idx, pro;
        
        Process(int idx, int pro){
            this.idx = idx;
            this.pro = pro;
        }
    }
    
    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Process> queue = new LinkedList<>();
        
        for(int i=0; i<priorities.length; i++) {
            queue.offer(new Process(i, priorities[i]));
            pq.offer(priorities[i]);
        }
        
        int answer = 1;
        while(!queue.isEmpty()) {
            Process process = queue.poll();
            if(pq.peek() > process.pro) queue.offer(process);
            else{
                if(process.idx == location) break;
                pq.poll();
                answer++;
            }
        }
    
        return answer;
    }
    
}