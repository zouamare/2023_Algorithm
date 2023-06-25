package day0319;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Solution_순위검색2 {

	public static void main(String[] args) {
		int[] s = solution(new String[] {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"}, new String[] {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"});
		System.out.println(Arrays.toString(s));
	}
	
	static HashMap<String, List<Integer>> map;
	
	public static int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
        map = new HashMap<String, List<Integer>>();
 
        // info를 통해 나올 수 있는 경우를 미리 구하기
        for (int i = 0; i < info.length; i++) {
            makeCase(info[i].split(" "), "", 0);
        }
 
        // key마다 List형태의 value가 존재하는데 이를 오름차순 정렬하기
        for (String key : map.keySet()) {
        	Collections.sort(map.get(key));        	
        }
 
        // query(조건)을 이진탐색하여 해당하는 info 개수 찾기
        for (int i = 0; i < query.length; i++) {
            String[] con = query[i].replaceAll(" and ", "").split(" ");
            answer[i] = map.containsKey(con[0]) ? search(con[0], Integer.parseInt(con[1])) : 0;
        }
 
        return answer;
    }
	
	// 이진탐색 : 2분의 1씩 탐색하는 범위를 줄여나가면서 탐색하는 것
	// 하나씩 비교해주면 시간초과 발생하므로 이진탐색 알고리즘 사용
    private static int search(String key, int score) {
        List<Integer> list = map.get(key);
        int start = 0, end = list.size() - 1;
 
        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < score)
                start = mid + 1;
            else
                end = mid - 1;
        }
 
        return list.size() - start;
    }
 
    // info가 포함될 수 있는 경우 만들기
    private static void makeCase(String[] st, String str, int cnt) {
        if (cnt == 4) {
            if (!map.containsKey(str)) {
                List<Integer> list = new ArrayList<Integer>();
                map.put(str, list);
            }
            map.get(str).add(Integer.parseInt(st[4]));
            return;
        }
        makeCase(st, str + "-", cnt + 1);
        makeCase(st, str + st[cnt], cnt + 1);
    }
}
