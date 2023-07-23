package coding_test.AlgoY2023.M07.D23;

import java.util.*;

public class 호텔방배정 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(10, new long[]{1, 3, 4, 1, 3, 1})));
    }

    static Map<Long, Long> map;
    static public long[] solution(long k, long[] room_number) {
        // 비어있다면, 배정
        // 비어있지 않다면, 원하는 방보다 크면서 비어있는 방 중 가장 번호가 작은 방
        long[] answer = new long[room_number.length];
        map = new HashMap<>();

        for(int i = 0; i < room_number.length; i++){
            answer[i] = getNewValue(room_number[i]);
        }

        return answer;
    }

    static private long getNewValue(long key){
        if(map.getOrDefault(key, 0L) == 0L){
            map.put(key, key + 1);
            return key;
        }

        map.put(key, getNewValue(map.get(key)));

        return map.get(key);
    }
}
