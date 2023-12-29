import java.util.*;

class Solution {
    
    public long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        Map<Long, Long> map = new HashMap<>();
        long[] answer = new long[n];
        
        for(int i=0; i<n; i++){
            long room = room_number[i];
            
            // 원하는 방이 비어있는 경우
            if(!map.containsKey(room_number[i])){
                answer[i] = room;
                map.put(room, findRoom(map, room+1));
                continue;
            }
            
            // 원하는 방이 이미 배정된 경우
            long next = findRoom(map, room);
            answer[i] = next;
            map.put(next, findRoom(map, next+1));
        }        

        return answer;
    }
    
    private long findRoom(Map<Long, Long> map, long room){
        if(!map.containsKey(room)) return room;
        long next = findRoom(map, map.get(room));
        map.put(room, next);
        return next;
    }
    
}