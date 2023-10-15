package coding_test.AlgoY2023.M03.D12;

import java.util.*;

public class PG_3차압축 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("A")));
    }

    public static int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        ArrayList<Integer> ans = new ArrayList<>();
        int num = 27, len = msg.length();

        // 1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화
        for(int i = 0; i < 26; i++){
            map.put(String.valueOf( (char) ('A' + i)) , i + 1);
        }

        int start = 0, end = start;

        while(start < len){
            // 2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 w을 찾는다.
            for(int i = start + 1; i <= len; i++){
                if(map.get(msg.substring(start, i)) == null || ((start == end || start == end - 1) && i == len)) {
                    end = i;
                    break;
                }
            }

            if(end == len && map.get(msg.substring(start, end)) != null){
                ans.add(map.get(msg.substring(start, end)));
                break;
            }

            // 3. w에 해당하는 사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
            ans.add(map.get(msg.substring(start, end - 1)));
            // 4. 입력에서 처리되지 않는 다음 글자가 남이있다면 (c), w+c에 해당하는 단어를 사전에 등록한다.
            map.put(msg.substring(start, end), num++);

            start = end - 1;
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}

