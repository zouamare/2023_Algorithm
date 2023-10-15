import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        
        // 1. 오름차순 정렬
        Arrays.sort(timetable);
        
        // 2. 시간 변환
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<timetable.length; i++){
            StringTokenizer st = new StringTokenizer(timetable[i], ":");
            queue.offer(Integer.parseInt(st.nextToken())*60 + Integer.parseInt(st.nextToken()));
        }
        
        // 3. 09:00(540)부터 m명씩 t분 간격 n회
        boolean isFull = false;
        int n_cnt = 0;
        int time = 540;
        int last = 0;
        while(n_cnt < n){
            if(n_cnt != 0) time += t;
            int m_cnt = 0;
            
            // 해당 시간에 탑승할 사람이 있는지, 자리가 있는지 확인 후 탑승
            while(!queue.isEmpty() && m_cnt < m){
                if(queue.peek() > time) break;
                // 막차까지 못타는 경우
                if(n_cnt == n-1 && m_cnt == m-1) {
                    last = queue.poll();
                    isFull = true;
                    break;
                }
                
                last = queue.poll();
                m_cnt++;
            }
            n_cnt++;
        }
        
        int tmp = 0;
        if(isFull) tmp = last-1;
        else tmp = 540 + (n-1)*t;
        
        String answer = String.format("%02d:%02d", tmp/60, tmp%60);
        return answer;
    }
}