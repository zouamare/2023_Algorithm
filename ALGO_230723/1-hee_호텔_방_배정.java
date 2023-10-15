import java.util.*;

class Solution {
   
   // 비어있는 방을 찾기 위한 Union-Find 메서드, Map을 사용해서 탐색!
   private static long findParent(HashMap<Long, Long> table, long x){
        if(table.getOrDefault(x, -1L) < 0L ) {
            table.put(x, x+1);
            return x;
        }else {
            table.put(x, findParent(table, table.get(x)));
            return table.get(x);
        }
    }


    public long[] solution(long k, long[] room_number) {        
        long[] answer = new long[room_number.length]; // 정답 배열을 선언하고
        HashMap<Long, Long> hmap = new HashMap<>(); // 빈 해쉬맵으로 출발

		// 고객의 수만큼 순회하면서
        for(int i = 0, size = room_number.length ; i <  size ; i++){
            long res = findParent(hmap, room_number[i]); // 메서드를 통해 배정 번호를 받고
            // 내부적으로 배정받은 번호 + 1 을 hmap에 저장하는 로직이 포함되어 있음!
            answer[i] = res; // 배정받은 번호를 배열에 기록한다.
        }

        return answer; // 정답 배열을 리턴
    }   
}