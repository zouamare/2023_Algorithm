import java.util.*;

class Solution {
    
    static int answer;
    static boolean[] visited;
    static int[] weaks, selected;
    
    public int solution(int n, int[] weak, int[] dist) {
        
        // 1. week 점검 시작 위치 구하기(원형 배열)
        weaks = new int[weak.length*2];
        for(int i=0; i<weak.length; i++){
            weaks[i] = weak[i];
            weaks[i+weak.length] = weak[i]+n;
        }
    
        // 2. 최소 인원 구하기
        answer = dist.length+1;
        visited = new boolean[dist.length];
        selected = new int[dist.length];
        for(int i=0; i<weak.length; i++){
            perm(i, 0, dist);
        }
        
        return (answer == dist.length+1) ? -1 : answer;
    
    }
    
    private void perm(int start, int cnt, int[] dist){
        if(cnt == dist.length){
            answer = Math.min(answer, check(start, start+weaks.length/2));
            return;
        }
        
        for(int i=0; i<dist.length; i++){
            if(visited[i]) continue;
            visited[i] = true;
            selected[cnt] = dist[i];
            perm(start, cnt+1, dist);
            visited[i] = false;
        }
    }
    
    private int check(int start, int end){
        int order = 0;
        int loc = weaks[start] + selected[order];
        
        for(int i=start; i<end; i++){
            
            // 점검할 수 있는 구역을 벗어난 경우(친구 추가 투입)
            if(loc < weaks[i]){
                order++;
                if(order == selected.length) return selected.length+1;
                loc = weaks[i] + selected[order];
            }
        }
        
        return order+1;
    }
    
}