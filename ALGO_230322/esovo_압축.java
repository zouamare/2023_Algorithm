package day0319;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_압축 {

	public static void main(String[] args) {
		int[] s = solution("KAKAO");
		System.out.println(Arrays.toString(s));
	}

	static Map<String, Integer> map = new HashMap<>();
	public static int[] solution(String msg) {
		ArrayList<Integer> list = new ArrayList<>();
		init();
		
		int idx = 27;
        int start = 0;
        int len = msg.length(); 
        while(start < len){
        	// 마지막 문자인 경우
            if(start == len-1){
                list.add(map.get(String.valueOf(msg.charAt(start))));
                break;
            }

            String w = String.valueOf(msg.charAt(start));
            start++;
            
            while(true){
            	// 다음 문자가 없는 경우
                if(start >= len) break;
                
                String c = String.valueOf(msg.charAt(start));
                // w+c가 사전에 없는 경우 추가
                if(!map.containsKey(w + c)) {
                    map.put(w + c, idx);
                    idx++;
                    break;
                }
                // w+c가 사전에 이미 있는 경우 다음 문자 탐색 반복
                w += c;
                start++;
            }
            
            // w에 해당하는 사전의 색인 번호를 list에 추가
            list.add(map.get(w));
        }
		
		int[] answer = new int[list.size()];
		for(int j=0; j<list.size(); j++) {
			answer[j] = list.get(j);
		}
		
		return answer;
	}

	// 초기 사전 초기화
	private static void init() {
		for (int i = 65; i <= 90; i++) {
			char alp = (char) i;
			map.put(String.valueOf(alp), i - 64);
		}
	}
}
