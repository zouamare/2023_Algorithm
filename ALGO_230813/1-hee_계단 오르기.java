package com.java.week17;
import java.util.*;
import java.io.*;

public class Main1 {
	
	/*
	 *
계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
마지막 도착 계단은 반드시 밟아야 한다.
	 */
		
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("data/boj2579.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		Arrays.fill(dp, 0);
				
		for(int i = 0 ; i < n ; i ++) {
			int x = Integer.parseInt(br.readLine());			
			stair[i] = x;
		}
		
		dp[0] = stair[0];
		dp[1] = Math.max(stair[0]+stair[1], stair[1]);
		dp[2] = Math.max(stair[1]+stair[2], stair[0]+stair[2]);

		N = n;
		solve(3);
		System.out.println(dp[n-1]);
					
	}
	
	static int[] dp = new int[301], stair = new int[301];	
	static int N;
	static void solve(int idx) {
		if(idx >= N) return;		
		
		dp[idx] = Math.max(
				dp[idx-2]+stair[idx], 
				stair[idx-1]+stair[idx]+dp[idx-3]
		);
		solve(idx+1);
	}
}

