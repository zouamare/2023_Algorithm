package com.java.week16;

import java.util.*;

public class Solution1 {
    
    static int result;
    static boolean[] binary;
    static int treeLen;
    
    public int[] solution(long[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        
        for(int i = 0; i < n ; i ++){
            String x = Long.toBinaryString(numbers[i]);
            
            int length = x.length(); // 이진수로 변환한 문자열의 길이를 구하고
            int exp = 1; // 포화 트리 높이를 구하기 위한 지수 값 변수
            
            // 포화 2진 트리의 높이를 구함
            // 1, 3, 5.. 이런식으로 length 보다 큰 높이를 찾을때까지 반복
            do{
                treeLen = (int) Math.pow(2, exp++) - 1;
            }while(treeLen < length);
            
            // 포화 트리 높이대로 배열을 생성,
            // 초기값 = false 을 사용해 더미 노드 = false로 세팅하는 효과
            binary = new boolean[treeLen];
            int idx = treeLen - length; 
            // 더미노드를 제외하고 이진수 값을 순서대로 채워 넣기 위해 시작 index를 구함
            
            for(int j = 0 ; j < length ; j ++){
                binary[idx++] = x.charAt(j) == '1'; // 변환된 이진수 문자열을 비교분석하여 트리 배열에 값 넣어줌
            }
            
            result = 1;            
            possible(0, treeLen-1, false); // 재귀함수를 통해 유효성 검증
            answer[i] = result;    // 결과 저장
        }
        
        return answer;
    }
    
    
    public static void possible(int s, int e, boolean check){
        int mid = (s+e)/2; // 루트 노드부터 탐색하게 됨
        
        if(check && binary[mid]){ // 루트가 0이면 자식노드들에서 1이나오면 안됨
            result = 0;
            return;
        }
        
        if(s!=e){
            possible(s, mid-1, !binary[mid]); // 왼쪽, 현재 루트가 더미이면 check = true
            possible(mid+1, e, !binary[mid]); // 오른쪽
        }
        
    }
    
    public static void main(String[] args) {
    	
    	Solution1 sol = new Solution1();    	    	
		long[] number1 = new long[] {7,42,5};		
		int[] ans1 = sol.solution(number1);		
		System.out.println(Arrays.toString(ans1));
		
	}
    
}