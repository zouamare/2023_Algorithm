package com.java.week12;

import java.util.Arrays;

public class Solution2 {
		
	public static int solution(int[] picks, String[] minerals) {
		
		// 풀이 : 곡괭이 전체 수만큼 배열을 만들고, NextPermutaion을 사용해서 dfs처리하는 것!
		// 재귀를 사용한 dfs라고 볼순 있는데 순열, 넥스트 순열을 써본 케이스
		
		// stream을 통해 전체 곡괭이 갯수를 셈합니다.
		int size = Arrays.stream(picks).sum();		

		int[] index = new int[size];
		int idx = 0;

		// 곡괭이의 종류는 3가지, 곡괭이를 보유 수량만큼 곡괭이 배열을 만듭니다.
		for(int i = 0 ; i < 3 ; i ++) {
			int cnt = picks[i];
			while(cnt > 0) {
				index[idx++] = i;
				cnt--;
			}
		}
		
		
		int minFatigue = 99999; // 최소연산 할거라서 아무거나 큰값 대충 init

		do {
			// 넥스트 순열 한번 돌때마다 최소 피로도 비교연산을 합니다.
			minFatigue = Math.min(minFatigue, mining(index, minerals));						
		}while(np(index));
						
		return minFatigue;
	}

	
	// 광물을 캐는 시뮬레이션 메서드, np 한번 돌 때마다 주어지는 곡괭이 종류로
	// 광물을 캐보고 소모된 피로도를  리턴하는 함수입니다.
	private static int mining(int[] indexs, String[] minerals) {
		int totalFatigue = 0; // 총 피로도
		int idx = 0; // 곡괭이 배열 조회용 index 계수
		int cnt = 5; // 곡괭이 내구도
		
		for(int m=0; m < minerals.length; m++) { // 광물 배열을 순회					
			totalFatigue += getFatigue(indexs[idx], minerals[m]); // 하드코딩이긴 한데, 곡괭이 번호 & 곡괭이 종류로 소모된 피로도 계산하는 메서드
			cnt--; // 내구도 감소처리
			if(cnt == 0) { // 내구도 다됐다.
				cnt = 5; // 내구도 새로 채워주고
				idx ++; // index 증가 시킴
				if(idx == indexs.length) return totalFatigue; // 곡괭이 전부 다 써서 작업 불가인 경우도 작업 종료로 해야함!
			}						
		}				 
		return totalFatigue; // 정상종료시의 피로도 리턴
	}
	
	private static int getFatigue(int no, String mineral) {		
		
		/*
		 * 곡괭이/광물   다이아 |  철   |  돌
		 * 다이아			1		1		1
		 * 철			5		1		1
		 * 돌			25		5		1
		 */
		
		// 위의 표를 그냥 if문으로 하드코딩함 ㅎㅎ		
		if(no == 0) { // 다이아 곡괭이
			return 1;			
		}else if(no == 1) { // 철 곡괭이			
			switch(mineral) {
				case "diamond":
					return 5;
				case "iron":
					return 1;
				default:
					return 1;
			}
			
		}else { // 돌곡괭이
			switch(mineral) {
				case "diamond":
					return 25;
				case "iron":
					return 5;
				default:
				return 1;			
			}
		}
	}
	
	
	// next permutation
	private static boolean np(int[] number) {
		int N = number.length;
		int i = N-1;
		
		while(i>0 && number[i-1] >= number[i]) --i;
		if(i==0) return false;

		int j = N-1;
		while(number[i-1] >= number[j]) --j;		
		swap(number, i-1, j);
		
		int k = N-1;
		while(i<k) swap(number, i++, k--);
		return true;		
	}
	 
	private static void swap(int[] number, int i, int j) {
		int temp = number[i];
		number[i] = number[j];
		number[j] = temp;		
	}


	public static void main(String[] args) {
		/*
		 * 
		 * [1, 3, 2]	["diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"]	12
		 * [0, 1, 1]	["diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"]	50
		 * dia, iron, stone
		 * 
		 */
		
		int ans1 = solution(new int[] {1,3,2}, new String[] {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"});		
		int ans2 = solution(new int[] {0, 1, 1}, new String[] {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"});		
				
		System.out.println(ans1);
		System.out.println(ans2);

	}

}
