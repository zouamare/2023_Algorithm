class Solution {
    
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[m][n];
        
        // 장애물 처리
        for(int i=0; i<puddles.length; i++){
            dp[puddles[i][0]-1][puddles[i][1]-1] = -1;
        }
        
        // 초기화
        for(int i=1; i<m; i++) {
            if(dp[i][0] == -1) break;
            dp[i][0] = 1;
        }
        for(int i=1; i<n; i++){
            if(dp[0][i] == -1) break;
            dp[0][i] = 1;
        }
        
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(dp[i][j] == -1) continue;
                if(dp[i-1][j]!=-1 && dp[i][j-1]!=-1) dp[i][j] = (dp[i-1][j]+dp[i][j-1]) % 1000000007;
                else if(dp[i-1][j]==-1 && dp[i][j-1]!=-1) dp[i][j] = dp[i][j-1];
                else if(dp[i-1][j]!=-1 && dp[i][j-1]==-1) dp[i][j] = dp[i-1][j];
                else dp[i][j] = 0;
            }
        }
        
        
        return dp[m-1][n-1];
    }
    
}