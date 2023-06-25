package com.java.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Solution1 {
	
	/*
	 * 풀이전략
	 * 
	 * 이 문제의 키 포인트 
	 * 명령어가 주어지면 그 명령어에 해당하는 결과의 개수를 조회해야한다.
	 * 자료구조 중 하나인 맵처럼 key-value 쌍으로 값을 조회 가능하게 해야한다.
	 * 맵에서 key = query가 되는 셈!
	 * 
	 * 풀이전략
	 * 1-1. 명령어(query)의 모든 경우의 수 구하기 by subset
	 * 1-2. HashMap을 통해 명령어 풀을 관리 >> key - value 식으로 값을 조회할 것이므로
	 * 2. 이분탐색을 통해 검색 속도를 개선 
	 * 
	 */
	
	private static HashMap<String, List<Integer>> hmap;

	
	// step 1.
	// 주어진 명령어로부터 만들 수 있는 모든 명령어의 경우의 수를 만드는 메서드
	// 부분집합 로직을 응용한 메서드로, 부분집합에서 필요한 방문체크 배열을
	// hashMap의 containsKey 메서드로 대체하였고, 
	// 재귀부분에서 방문체크를 하는 것이 아니라 기저조건에서 
	// 재귀부분 >> "-"를 포함하여 명령어를 던지거나 or "-"가 아닌 원문으로 문자열을 던진다
	// 종료 조건(if절) >> 명령어절이 총 4마디 이므로 N의 개수는 4이다.
	// 따라서, 카운트 계수가 4일 때 재귀가 종결되면 된다. (= 재귀의 최대 깊이는 4이다)
	
	// 토큰화된 단어가 담긴 어절 배열(어절 영어로 어절임), 재귀를 반복하며 늘려갈 문장 파라미터(sentence), 카운트 계수
//	private static void makeSentence(String[] eojeol, String sentence, int cnt) {
//		if(cnt == 4) {
//			if(!hmap.containsKey(sentence)) { // 방문체크 겸 중복체크, 안하면 리스트 무적권 초기화 ㄷㄷ
//				List<Integer> list = new ArrayList<>();
//				hmap.put(sentence, list);
//			}
//			hmap.get(sentence).add(Integer.parseInt(eojeol[4]));
//			return;			
//		}
//		makeSentence(eojeol, sentence + "-", cnt+1); // 와일드카드
//		makeSentence(eojeol, eojeol[cnt], cnt+1); // 원문
//	}
	
	 // info가 포함될 수 있는 문장
    private static void makeSentence(String[] p, String str, int cnt) {
        if (cnt == 4) {
            if (!hmap.containsKey(str)) {
                List<Integer> list = new ArrayList<Integer>();
                hmap.put(str, list);
            }
            hmap.get(str).add(Integer.parseInt(p[4]));
            return;
        }
        makeSentence(p, str + "-", cnt + 1);
        makeSentence(p, str + p[cnt], cnt + 1);
    }
	
	
	// step 2.
	// 이분 탐색
	private static int binarySearch(String key, int score) {		
		List<Integer> list= hmap.get(key);

		int start = 0, end = list.size()-1;
		
		while(start <= end) {
			int mid = (start+end)/2;
			if(list.get(mid) < score) { // 찾는 점수가 중위수보다 작을 경우 최소값 증가
				start = mid + 1;
			}else { // 찾는 점수가 중위수보다 작거나 같을 경우 최대값 감소;
				end = mid - 1;
			}
		}
		return list.size() - start;
		
	}
	

	

	public static int[] solution(String[] info, String[] query) {		
		
		int[] answer = new int[query.length];
		hmap = new HashMap<String, List<Integer>>();
//		
		// 인풋(info) 값의 전처리
		for(int i = 0 ; i < info.length; i ++) {
			String[] eojeol = info[i].split(" ");
			makeSentence(eojeol, "", 0); // 부분집합 시작!			
		}
		// 입력 값의 오름차순 정리 (for 이진 탐색)
		for(String key : hmap.keySet()) {
			Collections.sort(hmap.get(key));
		}

		// 명령어 (query)의 전처리
		for(int i = 0 ; i < query.length; i ++) {
			query[i] = query[i].replaceAll(" and ", ""); // // 토큰화를 편리하게 하기 위해 쿼리문을 전처리
			String[] q = query[i].split(" "); 			
			answer[i] = hmap.containsKey(q[0]) ? binarySearch(q[0], Integer.parseInt(q[1])) : 0;
		}
		 
        return answer;
	
    }
	
	/*
	 * [1,1,1,1,2,4]
	 */
	
	public static void main(String[] args) {
		
		TreeMap<Integer, String> tmap = new TreeMap<>();
		
		String[] info1 = {"java backend junior pizza 150", 
				"python frontend senior chicken 210", 
				"python frontend senior chicken 150", 
				"cpp backend senior pizza 260",
				"java backend junior chicken 80", 
				"python backend senior chicken 50"};
		String[] query1 = {
				"java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200",
				"cpp and - and senior and pizza 250",
				"- and backend and senior and - 150",
				"- and - and - and chicken 100",
				"- and - and - and - 150"};
		
		int[] answer = solution(info1, query1);
		System.out.println(Arrays.toString(answer));
	}

}
