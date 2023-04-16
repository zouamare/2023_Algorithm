import java.util.*;

/*
    m > timetable 이면 그냥 제일 늦은시간 타고가면된다.
    그게 아니라면 PQ에 timetable을 다 집어넣고 m만큼 쳐내고 막차에서 시간을 결정한다.
    m > 막차인원 이면 도착시간에 타고가면 된다.
    
    
    일단 인풋 데이터 정제가 필요할것같은데 9시에 t * (n - 1)더한시간 이후에 있는 timetable는 다 없애고 시작해도 무방하다
    왜? 어차피 그시간엔 버스가 없거든
    
*/

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        // 버스도착하는 시간
        int[] busTime = new int[n];
        
        for (int i = 0; i < n; ++i) {
            busTime[i] = toMinute("09:00") + t * i;
        }
        
        // 자리넉넉하면 막차타고가~
        if (m > timetable.length) {
            return toTimestamp(busTime[busTime.length - 1]);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // 시간은 분단위로 관리할거임
        for (String timestamp: timetable) {
            int minute = toMinute(timestamp);
            if (busTime[busTime.length - 1] < minute) continue;
            pq.add(minute);
        }
        
        // 막차전까진 보낼사람 다 보내
        for (int bus = 0; bus < busTime.length - 1; ++bus) {
            int passenger = 0;
            while (!pq.isEmpty() && pq.peek() <= busTime[bus] && passenger < m) {
                pq.poll();
                passenger++;
            }
        }
        
        // 막차자리가 남으면 도착시간에 맞춰가
        if (m > pq.size()) {
            return toTimestamp(busTime[busTime.length - 1]);
        }
        
        int passenger = 0;
        // TreeSet쓰는건 제일 늦은시간 찾아서 - 1분 해주려고 하는거임 이진탐색트리 개꿀 ㅇㅈ?
        TreeSet<Integer> inBus = new TreeSet<>();
        
        // 탑승인원까지 꽉채워서 태워
        while (!pq.isEmpty() && pq.peek() <= busTime[busTime.length - 1] && passenger < m) {
            inBus.add(pq.poll());
            passenger++;
        }
        
        // 탄놈들중에 제일 늦은애(중복되어도 상관이 없음 제일 늦은사람(들) 한명 자리 뺏어서 1분 일찍오게 하는거라서)자리 뺏어
        int answer = inBus.last() - 1;
        return toTimestamp(answer);
    }
    
    static int toMinute(String timestamp) {
        StringTokenizer st = new StringTokenizer(timestamp, ":");
        return Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
    }
    
    static String toTimestamp(int minute) {
        return String.format("%02d:%02d", minute / 60, minute % 60);
    }
}