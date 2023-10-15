package coding_test.AlgoY2023.M04.D23;

import java.util.*;

class 할인행사 {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < discount.length; i++){
            map.put(discount[i], map.getOrDefault(discount[i], 0) + 1);
            if(i >= 10){
                map.put(discount[i - 10], map.getOrDefault(discount[i - 10], 0) - 1);
            }

            boolean flag = true;
            for(int j = 0; j < number.length; j++){
                if(map.getOrDefault(want[j], 0) < number[j]){
                    flag = false;
                    break;
                }
            }

            if(flag){
                answer++;
            }
        }

        return answer;
    }
}