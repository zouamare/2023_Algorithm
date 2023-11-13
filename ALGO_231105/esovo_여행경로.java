import java.util.*;

class Solution {
    
    class Airport{
        int idx;
        String port;
        
        Airport(int idx, String port){
            this.idx = idx;
            this.port = port;
        }
    }
    
    static int N;
    static boolean[] visited;
    static TreeSet<String> treeSet;
    static Map<String, List<Airport>> ticketsMap;
    public String[] solution(String[][] tickets) {
        N = tickets.length;
        visited = new boolean[N];
        treeSet = new TreeSet<>();
        ticketsMap = new HashMap<>();
        
        // 출발 공항으로 도착 공항을 찾을 수 있도록 HashMap 사용
        for(int i=0; i<N; i++){
            if(!ticketsMap.containsKey(tickets[i][0])) ticketsMap.put(tickets[i][0], new ArrayList<>());
            ticketsMap.get(tickets[i][0]).add(new Airport(i, tickets[i][1]));
        }
        
        // ICN에서 시작
        dfs(0, "ICN");
        
        String ports = treeSet.first();
        int len = ports.length()/3;
        String[] answer = new String[len];
        for(int i=0; i<len; i++){
            answer[i] = ports.substring(i*3, i*3+3);
        }
        return answer;
    }
    
    private void dfs(int cnt, String str){
        if(cnt == N){
            treeSet.add(str);
            return;
        }
        
        String cur = str.substring(str.length()-3, str.length());
        // 출발 공항이 없는 경우
        if (!ticketsMap.containsKey(cur)) return;
        
        for(Airport airport : ticketsMap.get(cur)){
            // 이미 사용한 항공권인 경우
            if(visited[airport.idx]) continue;
    
            visited[airport.idx] = true;
            dfs(cnt+1, str+airport.port);
            visited[airport.idx] = false;
        }
    }
    
}