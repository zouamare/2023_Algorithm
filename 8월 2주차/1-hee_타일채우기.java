package com.java.week17;

import java.util.*;
import java.io.*;

public class Main2 {

	// 참고 : https://yabmoons.tistory.com/536
	// 3×N 크기의 벽을 2×1, 1×2 크기의 타일로 채우는 경우의 수를 구해보자.	
	private static int [][] tile;
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("data/boj2133.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int ans = solve(n);
		System.out.println(ans);
		
	}
	static int N, MAX = 30;
	static int[] dp;
	
	static int solve(int n) {
		if(n%2==1) return 0; // 홀수인 경우에는 경우의 수를 구할 수 없다!
		
		dp = new int[MAX];
		dp[0] = 1;
		dp[1] = 0;
		dp[2] = 3;
		
		// 3부터 시작하지 않는 이유는, 3은 있을 수 없다 == 0이다.
		for(int i = 4 ; i <= n ; i += 2) {
			dp[i] = dp[i-2]*dp[2];
			
			for(int j = i-4 ; j >= 0 ; j-=2) {
				dp[i] = dp[i] + (dp[j]*2);
			}
			
		}		
		return dp[n];				
	}

}
