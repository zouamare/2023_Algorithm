package coding_test.AlgoY2023.M08.D13;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 타일채우기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] DP = new int[N + 1];

        if(N % 2 == 1){
            System.out.println(0);
            return;
        }

        DP[0] = 1;  // 아무것도 채우지 않은 경우의 수 1
        DP[1] = 0;  // 홀수는 채울 수 없음
        DP[2] = 3;  // 경우의 수 3

        for(int i = 4; i <= N; i += 2){
            DP[i] = DP[i-2] * DP[2];
            for(int j = i - 4; j >= 0; j -= 2){
                DP[i] = DP[i] + (DP[j] * 2);
            }
        }

        System.out.println(DP[N]);
    }
}
