import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        // 차량 나간 지점으로 오름차순 정렬
        Arrays.sort(routes, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
              }
        });
        
        int answer = 1;
        int end = routes[0][1];
        for(int i=1; i<routes.length; i++){
            if(routes[i][0] <= end) continue;
            end = routes[i][1];
            answer++;
        }
        
        return answer;
    }
}