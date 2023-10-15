package com.java.temp;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		// System.setIn(new FileInputStream("data/test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] numbers = new int[n+1]; // numbers
		int[] Dp = new int[n+1]; // Dp Array
			
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n ; i ++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		Dp[1] = 1; // 첫번째 원소 init 해주고		
		int max = Dp[1]; // 초기 값은 최소 값이 1이므로 첫번째 Dp 값을 넣음
						
		for (int i = 2; i <= n; i++) { // 두번째부터 시작
            Dp[i] = 1; // 최소값인 자기자신을 세팅하고,
            for (int j = 0; j < i; j++) {  // 처음부터 지금 위치까지 순회하면서           	
                if (numbers[i] < numbers[j] // 앞에 위치한 원소가 자신보다 작고 
                		&& Dp[i] <= Dp[j]) { // Dp 배열의 값이 지금의 값보다 작거나 같다면                	
                    Dp[i] = Dp[j] + 1; // 그때는 증가를 시켜주고
                } else if (numbers[i] == numbers[j]) { // 같을 경우에는
                    Dp[i] = Dp[j]; // 복사
                }
                // 아닌 경우는 냅둬서 1인 경우도 만들어야함!
            }            
	    }
		
		for(int i : Dp) { // 최대 값은 Dp 배열의 가장 마지막에 위치하지 않을 수 있으므로
			max = Math.max(max, i); // 대소비교 후
		}
		
		System.out.println(max); // 결과 출력		
	}
}
