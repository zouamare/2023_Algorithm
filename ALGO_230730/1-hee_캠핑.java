package com.java.week16;

import java.util.*;

public class Solution2 {
	
    int MAX = 5001;
    public int solution(int n, int[][] data) {
        
        int answer = 0;
        int[][] dp = new int[this.MAX][this.MAX];
        
        // 좌표 압축
        ArrayList<Integer> xList = new ArrayList<>();
        ArrayList<Integer> yList = new ArrayList<>();
        
        // System.out.println("------ init ---- ");
        for(int[] p : data){
            xList.add(p[0]);
            yList.add(p[1]);
            // System.out.print(Arrays.toString(p));
        }
        // System.out.println();
        
        // 중복되는 좌표의 값은 필터링
        ArrayList<Integer> unXList = new ArrayList<>(new HashSet<Integer>(xList));
        ArrayList<Integer> unYList = new ArrayList<>(new HashSet<Integer>(yList));
        
        // 오름차 순으로 정렬해준다
        Collections.sort(unXList);
        Collections.sort(unYList);
        
        // ?
        for(int i = 0 ; i < n ; i ++){
            data[i][0] = unXList.indexOf(xList.get(i));
            data[i][1] = unYList.indexOf(yList.get(i));
            int r = data[i][1];
            int c = data[i][0];        
            
            // 주어진 테스트 케이스에 대해 마킹 작업!
            dp[r][c] = 1;           
        }
        
        
        // dp로 구간합 구하기
        for(int r = 0 ; r < MAX ; r++){
            for(int c = 0 ; c < MAX ; c++){
                if(r-1 >= 0){
                    dp[r][c] += dp[r-1][c];
                }                 
                if(c-1 >= 0){
                    dp[r][c] += dp[r][c-1];
                }
                // 중복 카운트 제거
                if(r-1 >= 0 && c-1 >= 0){
                    dp[r][c] -= dp[r-1][c-1];
                }
            }            
        }
        
        // 주어진 테스트 케이스에 대해서 오름차 순으로 탐색해야함
        // x
        Arrays.sort(data, (o1, o2)->{
            if(o1[0] == o2[0]){
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        
        // 데이터 배열 수만큼 2중 반복문을 통해
        // 모든 경우의 수에 대해 탐색한다.
        for(int i = 0 ; i < n ; i ++){
            for(int j = i+1 ; j < n ; j++){
            	// 각 좌표의 조합마다
            	// 좌표 배열에 담긴 좌표 값에 대해 대소 비교를 하여
            	// 최소값 = 시작점, 최대 값 = 끝점 으로 한다.
            	
            	// 시작점을 찾아준다
                int x1 = Math.min(data[i][0], data[j][0]);
                int y1 = Math.min(data[i][1], data[j][1]);
                // 끝점을 찾아준다
                int x2 = Math.max(data[i][0], data[j][0]);
                int y2 = Math.max(data[i][1], data[j][1]);
                
                if(x1 == x2 || y1 == y2) continue; // 기하학적(?) 으로 사각형이 생길 수 없으므로 건너뛴다 = 사각형 생성히 불가능한 경우
                
                
                int value1 = dp[y2-1][x2-1];
                int value2 = dp[y2-1][x1];
                int value3 = dp[y1][x2-1];
                int value4 = dp[y1][x1];
                // System.out.print(value1+", "+value2+", "+value3+", "+value4);
                
                int cnt = 
                    value1 // 끝점으로부터 행, 열 모두 1씩 앞 좌표인 경우에서
                    - value2 // 1만큼 앞의 행에서의 값과
                    - value3 // 1만큼 앞의 열에서의 값을 뺀 것에 => 중복을 모두 제거했는데
                    + value4; // 최소 좌표에서의 값을 더했다면
                
                // System.out.print("\t=>"+cnt+"\n");                
                if(cnt == 0) {
                    answer ++;
                }                    
            }
        }
        return answer;
    }
    
    
    public static void main(String[] args) {
    	int n1 = 4;
    	int[][] data1 = new int[][] {{0, 0}, {1, 1}, {0, 2}, {2, 0}};

    	Solution2 sol = new Solution2();
    	int ans = sol.solution(n1, data1);
    	System.out.println(ans); // 3
    	
	}

}
