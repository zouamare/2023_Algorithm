package coding_test.AlgoY2023.M09.D03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 가장_긴_감소하는_부분_수열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] DP = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            DP[i] = 1;
            for(int j = 0; j < i; j++){
                if(A[i] < A[j] && DP[i] < DP[j] + 1){
                    DP[i] = DP[j] + 1;
                }
            }
            result = Math.max(result, DP[i]);
        }

        System.out.println(result);
    }
}
