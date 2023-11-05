package coding_test.AlgoY2023.M11.D04;

public class 정수삼각형 {
    public int solution(int[][] triangle) {
        int[][] dp = new int[triangle.length][triangle[triangle.length - 1].length];
        System.out.println(triangle.length);
        System.out.println(triangle[triangle.length - 1].length);

        if(triangle.length > 0){
            dp[0][0] = triangle[0][0];
        }

        for(int i = 1; i < triangle.length; i++){
            for(int j = 0; j < triangle[i].length; j++){
                if(j == 0){ // 제일 왼쪽일 때
                    dp[i][j] = triangle[i][j] + dp[i - 1][j];
                }
                else if(j == triangle[i].length - 1){   // 제일 오른쪽일 때
                    dp[i][j] = triangle[i][j] + dp[i - 1][j - 1];
                }
                else{   // 그 외
                    dp[i][j] = Math.max(
                            triangle[i][j] + dp[i - 1][j - 1],
                            triangle[i][j] + dp[i - 1][j]
                    );
                }
            }
        }

        int answer = 0;
        for(int i = 0; i < dp[dp.length - 1].length; i++){
            answer = Math.max(dp[dp.length - 1][i], answer);
        }

        return answer;
    }
}
