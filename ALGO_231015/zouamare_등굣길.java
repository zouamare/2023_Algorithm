package coding_test.AlgoY2023.M10.D15;

public class 등굣길 {
    public int solution(int m, int n, int[][] puddles) {
        long[][] dp = new long[m + 1][n + 1];
        boolean[][] sinked = new boolean[m + 1][n + 1];

        for(int i = 0; i < puddles.length; i++){
            sinked[puddles[i][0]][puddles[i][1]] = true;
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <=n; j++){
                if(!sinked[i][j])dp[i][j] = dp[i-1][j] + dp[i][j-1];
                if(i == 1 && j == 1) dp[1][1] = 1;
                dp[i][j] %= 1000000007L;    // 매번 나눠줘야 효율성 통과됨
            }
        }

        return (int) (dp[m][n]);
    }
}
