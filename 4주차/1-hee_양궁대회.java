package com.java.week4;

import java.util.Arrays;

public class Solution2 {
	

	
	private static int[] res = {-1};
	private static int[] lion;
	private static int max = -99999; 	
	private static int N;
	
	/* 
	 * 라이언이 최대 차이로 승리할 수 있는 전략은 무엇인지 찾는 문제
	 * 
	 */
	
	private static void dfs(int[] apeach, int cnt) {
		if(cnt == N) {
			int ap_point = 0; // 어피치 점수
			int li_point = 0; // 라이언 점수
			
			for(int i = 0 ; i <= 10; i ++) {	
				// 어피치 또는 라이온이 획득한 점수가 있을 경우
				if(apeach[i] !=0 || lion[i] != 0) {					
					if(apeach[i] < lion[i]) {// 라이온이 이겼을 경우
						// 인덱스 0번 = 10 점 배열 내림차순이므로
						li_point += 10 - i; 												
					}else {// 라이온이 졌을 경우 (어피치 승리)
						ap_point += 10 - i;
					}										
				}				
			}
			
			// 결과 검증
			if(li_point > ap_point) { // 라이온이 승리하고
				if(li_point- ap_point >= max) {  // 둘의 차이가 최대라면
					res = lion.clone(); // 해당 과녁판으로 갱신하고
					max = li_point - ap_point; // 점수 차이를 기록한다.
				}
			}
			
			return;
		}
		
		// 10점부터 0점까지 dfs로 던져보는데,
		for(int i =0 ; 
				i <= 10 && 
				lion[i] <= apeach[i]; 
				// 점수는 어쨋든 1발만 우세하면 획득하는 것인데,
				// 이 조건을 안걸면 dfs에서 이미 이긴 점수에 또 투자하는 행동을 반복해서 터짐 >> 백트래킹!
				// 10의 10승이니까 100억번의 탐색을 하게 됨 >> 무조건 터짐....
				i++) {
			
			// 여기 질문 i < 11 / i <= 10의 차이가 무엇일까..?
			lion[i]++;
			dfs(apeach, cnt+1);
			lion[i]--;
			
		}
				
	}
	

	
	
    public static int[] solution(int n, int[] info) {
    	res = new int[] {-1};
    	max = -99999;
    	N = n;    	
    	lion = new int[11];
    	dfs(info, 0);
    	return res;
    	
    }
    
    
	public static void main(String[] args) {
		
		int[] ans1 = solution(5, new int[] {2,1,1,1,0,0,0,0,0,0,0}); // [0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0]
		int[] ans2 = solution(1, new int[] {1,0,0,0,0,0,0,0,0,0,0}); // [-1]
		int[] ans3 = solution(9, new int[] {0,0,1,2,0,1,1,1,1,1,1}); // [1,1,2,0,1,2,2,0,0,0,0]
		int[] ans4 = solution(10, new int[] {0,0,0,0,0,0,0,0,3,4,3}); // [1,1,1,1,1,1,1,1,0,0,2]
		
		System.out.println(Arrays.toString(ans1));
		System.out.println(Arrays.toString(ans2));
		System.out.println(Arrays.toString(ans3));
		System.out.println(Arrays.toString(ans4));
		
	}
	

}
