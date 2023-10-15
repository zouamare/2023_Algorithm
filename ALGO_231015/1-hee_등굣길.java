class Solution {
    
    private static final int CONSTANT = 1_000_000007;
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];
        
        for(int[] pos : puddles){
            int c = pos[0] - 1;
            int r = pos[1] - 1;
            dp[r][c] = -1;            
        }
        
        dp[0][0] = 1;
        
        for(int r = 0 ; r < n ; r++){
            for(int c = 0 ; c < m ; c ++){
                if(dp[r][c] < 0){
                    dp[r][c] = 0;
                    continue;
                }
                
                // 아래의 연산에서 CONSTANT을 넘어서는 overflow가 생길 수 있으므로
                // CONSTANT로 % 연산을 해줌
                if(r > 0){
                    dp[r][c] += dp[r-1][c] % CONSTANT; 
                }
                if(c > 0){
                    dp[r][c] += dp[r][c-1] % CONSTANT;
                }
            }
        }
        
        int answer = dp[n-1][m-1] % CONSTANT; 
        return answer;
    }
    
}