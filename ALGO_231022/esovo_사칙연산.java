import java.util.*;

class Solution {
    public int solution(String arr[]) {
        int n = (arr.length+1)/2;
        int[][] max = new int[n][n];
        int[][] min = new int[n][n];
        
        // 숫자와 연산자 분리
        int[] numbers = new int[n];
        char[] operators = new char[n-1];
        for(int i=0; i<arr.length; i++) {
            if (i%2 == 0) numbers[i/2] = Integer.parseInt(arr[i]);
            else operators[i/2] = arr[i].charAt(0);
        }
        
        // 배열 초기화
        for(int i=0; i<n; i++) {
            max[i][i] = numbers[i];
            min[i][i] = numbers[i];
        }
        
        // 부분식으로 최댓값, 최솟값 계산
        for(int len=2; len<=n; len++) {
            for(int i=0; i<=n-len; i++) {
                int j = i+len-1; // 부분식이 끝나는 지점
                max[i][j] = Integer.MIN_VALUE;
                min[i][j] = Integer.MAX_VALUE;
                
                for(int k = i; k < j; k++) {
                    int oper = operators[k]=='-' ? -1 : 1;
                    int a = max[i][k] + oper*max[k+1][j];
                    int b = max[i][k] + oper*min[k+1][j];
                    int c = min[i][k] + oper*max[k+1][j];
                    int d = min[i][k] + oper*min[k+1][j];
                    
                    max[i][j] = Math.max(max[i][j], Math.max(a, Math.max(b, Math.max(c, d))));
                    min[i][j] = Math.min(min[i][j], Math.min(a, Math.min(b, Math.min(c, d))));
                }
            }
        }
        
        return max[0][n-1];
    }

}
