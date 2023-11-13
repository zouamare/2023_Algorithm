class Solution {
    public int solution(int[][] triangle) {
        int len = triangle.length;
        int[][] dp = new int[len][triangle[len-1].length];
        
        // dp 초기화
        dp[0][0] = triangle[0][0];
        for(int i=1; i<len; i++){
            int idx = triangle[i].length-1;
            dp[i][0] = dp[i-1][0]+triangle[i][0];
            dp[i][idx] = dp[i-1][idx-1]+triangle[i][idx];
        }
        
        for(int i=2; i<len; i++){
            for(int j=1; j<triangle[i].length-1; j++){
                dp[i][j] = Math.max(dp[i-1][j-1]+triangle[i][j], dp[i-1][j]+triangle[i][j]);
            }
        }
        
        int answer = 0;
        for(int i=0; i<triangle[len-1].length; i++){
            answer = Math.max(answer, dp[len-1][i]);
        }
    
        return answer;
    }
}