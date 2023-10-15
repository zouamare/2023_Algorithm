package coding_test.AlgoY2023.M08.D13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 계단오르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] stairs = new int[N+1];
        int[] dp = new int[N+1];
        for(int i = 1; i <= N; i++)  stairs[i] = Integer.parseInt(br.readLine());

        dp[1] = stairs[1];

        if(N >= 2)  dp[2] = stairs[1] + stairs[2];

        // i - 2의 값과, i-3 + i-1 중에 더 큰 값을 구해서 현재 값에 더한다.
        // 연속으로 3개가 올 수 없으므로!
        for(int i = 3; i <= N; i++) dp[i] = Math.max(dp[i-2], dp[i-3] + stairs[i-1]) + stairs[i];

        System.out.println(dp[N]);
    }
}
