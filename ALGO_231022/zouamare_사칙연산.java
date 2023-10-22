package coding_test.AlgoY2023.M10.D22;

import java.util.Arrays;

public class 사칙연산 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"1", "-", "3", "+", "5", "-", "8"}));
    }

    public static int solution(String arr[]) {
        int operCnt = (int) Math.ceil((double) arr.length / 2);
        int[][] maxDP = new int[operCnt][operCnt];
        int[][] minDP = new int[operCnt][operCnt];

        for(int i = 0; i < operCnt; i++){
            Arrays.fill(maxDP[i], Integer.MIN_VALUE);
            Arrays.fill(minDP[i], Integer.MAX_VALUE);
            maxDP[i][i] = Integer.parseInt(arr[i * 2]);
            minDP[i][i] = Integer.parseInt(arr[i * 2]);
        }

        for(int cnt = 1; cnt < operCnt; cnt++){ // 비교 거리 구하는 변수
            for(int i = 0; i < operCnt - cnt; i++){ // 비교할 대상 1번
                int j = i + cnt;    // 비교할 대상 2번

                for(int k = i; k < j; k++){ // 연산자 위치 정하는 변수
                    if(arr[k*2+1].equals("+")){
                        maxDP[i][j] = Math.max(maxDP[i][j],
                                maxDP[i][k] + maxDP[k+1][j]);
                        minDP[i][j] = Math.min(minDP[i][j],
                                minDP[i][k] + minDP[k+1][j]);
                    }
                    else{
                        maxDP[i][j] = Math.max(maxDP[i][j],
                                maxDP[i][k] - minDP[k+1][j]);
                        minDP[i][j] = Math.min(minDP[i][j],
                                minDP[i][k] - maxDP[k+1][j]);

                    }
                }
            }
        }
        return maxDP[0][operCnt - 1];
    }
}
