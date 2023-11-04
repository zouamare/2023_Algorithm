import java.util.*;

class Solution {

    static boolean[] isVisisted;
    static int size;
    static ArrayList<String> routeList;
    public String[] solution(String[][] tickets) {
                
        size = tickets.length; // 최대 길이
        isVisisted = new boolean[size]; // 정점 방문 체크 리스트     
        routeList = new ArrayList<>(); // 가능한 모든 경로를 담을 arrayList
        
        dfs("ICN", "ICN", 0, tickets); 
        
        Collections.sort(routeList); // 알파벳 순서대로 담아야 하므로 정렬 실시        
        String[] answer = routeList.get(0).split(" "); // 공백을 기준으로 토큰화했으므로 공백으로 분리시킴.        
        return answer;
    }
    
    // 현재 정점, 다음 방문지, 현재 깊이
    private static void dfs(String start, String route, int cnt, String[][] tickets){                       
        if(cnt >= size){
            routeList.add(route);
            return;
        }
        
        for(int i = 0 ; i < size ; i ++){
            if( start.equals(tickets[i][0]) && !isVisisted[i] ){
                isVisisted[i] = true;
                dfs(tickets[i][1], route+" "+tickets[i][1], cnt+1, tickets);
                isVisisted[i] = false;
            }
        }        
    }
}