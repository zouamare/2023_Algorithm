package com.java.week16;

import java.util.*;

class Solution1 {
    
    // https://countrysides.tistory.com/80
    
    ArrayList<ArrayList<Integer>> info = new ArrayList<>();
    int[] gps_log;
    int[][] dp = new int[101][201]; // 거점 정보의 개수 x 거점의 총 개수(= 노드의 총 개수)
    int MAX = 999999;
    
    public void init(int n , int[][] edge_list, int[] gps_log){
        this.gps_log = gps_log;
        
        
        // dp 배열 사용 넓이?
        // row = gps_log 배열에 대해서는 fit 하게! = k
        // col = node의 배열에 대해서는 한칸 더 넉넉하게! = n+1
        
        // dp 배열 init!
        for(int i = 0, size = gps_log.length; i < size ; i ++){
            Arrays.fill(dp[i], -1); // 문제에서 사용할 만큼만 초기화
        }
        
        // step1. 연결 리스트를 생성하고, 
        // 자기 자신을 채워주기 for 정차하는 경우
        for(int i=0; i <= n; i ++){
            info.add(new ArrayList<>());
            info.get(i).add(i); // 정차하는 경우
        }
        
        // step 2. 문제에서 주어진 연결 정보를 기준으로 연결 리스트 채워
        for(int i = 0, size = edge_list.length; i < size ; i ++){
        	// 양방향 처리
            info.get(edge_list[i][0]).add(edge_list[i][1]);
            info.get(edge_list[i][1]).add(edge_list[i][0]);
        }
    }
    
    public int solve(int time, int now){

        // 마지막 지점은 변경 불가능이므로 1이 아닌 MAX를 return;
        if(time == gps_log.length-1){
            return dp[time][now] = ( gps_log[time]== now) ? 0 : MAX;
        }
        // 이미 구해진 최적해가 있다면
        if(dp[time][now] != -1){
            return dp[time][now];
        }
        
        // min은 최소를 구하기 위한 변수, 비교적 큰 값으로 초기화
        int min = MAX;
        dp[time][now] = 0; // gps_log 속 배열의 값에 해당하면, 바꾸지 않은 것이므로 가장 적은 비용인 0을 입력!
                
        for(int i=0, size = info.get(now).size(); i< size ; i++){// 현재 노드에서 방문 가능한 경우에 대해 모두 발산하며 재귀를 던진다.        	
            int next = info.get(now).get(i); // 방문 가능한 다음 노드!
            min = Math.min(solve(time+1, next), min); // 재귀함수를 던져서 최소 변경 횟수 값을 구해온다
        }
        
        // 변경해야하는 경우 +1
        // gps_log 배열을 기준으로, 
        // 현재 재귀 깊이에서의 최적해가 gps_log에 있는 노드의 번호와 같다면
        // min 값을 그대로 리턴하고, 그렇지 않으면 재귀를 통해 변경된 것이므로 변경횟수 +1 을 해준다.
        return dp[time][now] = ( gps_log[time] == now ) ? min : min + 1;
    }
    
    
     public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
         
         int answer = 0;
         init(n, edge_list, gps_log);
         answer = solve(0, gps_log[0]); // 최소 변경 횟수를 구하고,
         // 마지막으로 가는 경로가 없거나, 만족하는 해가 없다면
         answer = (dp[k-1][gps_log[k-1]] == -1) || dp[k-1][gps_log[k-1]] >= MAX ? -1 : answer;         
         
         
         ///         
         for(int i = 0 ; i < k ; i++) {
        	 System.out.print("k = "+i+"\t→  ");
        	 for(int j = 0 ; j <= n ; j++) {
        		 System.out.print(dp[i][j]+"\t");
        	 }
        	 System.out.println();
         }         
         ///
         return answer;
         
     }
     
     
     public static void main(String[] args) {
    	 int n1 = 7;
    	 int m1 = 10;
    	 int[][] edge_list1 = new int[][]{{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}};
    	 int k1 = 6;
    	 int[] gps_log1	= new int[] {1, 2, 3, 3, 6, 7}; // → 1, 2, 3, 5, 6, 7
    	 
    	 Solution1 sol = new Solution1();
    	 int ans1 = sol.solution(n1, m1, edge_list1, k1, gps_log1);
    	     	 
	}
     
     
     // 나의 풀이
//   private static final int MAX = 999999999;
//   private static int[][] node_matrix;
//   private static int N;
      
//   public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
      
//       node_matrix = new int[n+1][n+1];
//       N = n;
//       min = MAX;        
      
//       for(int[] pos : edge_list){
//           int from = pos[0];
//           int to = pos[1];
//           node_matrix[from][from] = 1;
//           node_matrix[to][to] = 1;
//           node_matrix[from][to] = node_matrix[to][from] = 1;
//       }
      
//       int from = gps_log[0];
//       int to = gps_log[1];        
//       dfs(0,from, to, 1, gps_log);
//       return min!=MAX ? min : -1;
//   }
  
//   private static int min = MAX;
//   private static void dfs(int cnt, 
//                           int from, int to, 
//                           int idx, int[] orders
//                          ){
      
//       if(idx == orders.length-1){
//           min = Math.min(cnt, min);
//           return;
//       }           
      
//       if(node_matrix[from][to]==0){ // 수정이 필요한 경우                        
          
//           boolean isEnd = true;            
//           for(int i = 1; i < N; i ++){
//               if(node_matrix[from][i] == 1){
//                   dfs(cnt+1, from, i, idx+1, orders);
//                   isEnd = false;
//               }
//           }             
          
//       }else { // 수정하지 않아도 갈 수 있는 경우
//           int nIdx = idx+1;            
//           int nFrom = to;
//           int nTo = orders[nIdx];            
//           dfs(cnt, nFrom, nTo, nIdx, orders);
//       }
      
//   }     
     

}