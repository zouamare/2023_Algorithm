package com.java.week8;

import java.util.*;

class Solution4 {
	
	// 못푼 이유
	// 1. player들의 좌표를 static으로 관리했더니 값이 중간에 꼬이고 난리났다.
	// 2. 경우의 수가 두가지라고 생각했다. a가 0이거나 b가 0이거아
	// 		이번 문제의 경우는 승패에 따른 경우의 수도 생각해야했는데 그 부분을 신경쓰지 못한 것 같다.
	
	// 이번 문제는 표면적으로는 dfs가 포함되어 었는데,
	// 내부를 보면 최대 최소 알고리즘(Minimax Algorithm)이 잘 녹아있는 문제이다.
	// 최대 최소 알고리즘(Minimax Algorithm) = 게임이론으로부터 나온 알고리즘으로, 체스나 바둑 할 때 제일 게임 오래하는 경우 찾기! 정도로 이해하면 될듯
	// 본 게임에서 이기든 지든 둘중 하나의 "결과"가 나오게 되는데,
	// 이 결과가 나와버리면 사실 게임의 종료를 의미하게 된다. 
	// 그러나 본 알고리즘의 목적은 게임의 종료를 최대한 늦추는것이 목표이다.
	
	private static int[] dx = {1,-1, 0, 0};
	private static int[] dy = {0,0, 1, -1};
	private static int max = 10_000_000;
	
	
	private static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
		
	private static class Result {
		boolean isWin; // 승패여부
		int round; // 이동한 거리 
		public Result(boolean isWin, int round) {
			this.isWin = isWin;
			this.round = round;
		}		
	}
		
    public static int solution(int[][] board, int[] aloc, int[] bloc) {
        
    	R = board.length;
    	C = board[0].length;
    	    	
    	Result result 
    		= dfs(
    				board,
    				new Point(aloc[0], aloc[1]),
    				new Point(bloc[0], bloc[1]),
    				true,
    				0);
    	
        return result.round;
    }
    
    
    private static int R, C;

    // 양 플레이어가 최적의 플레이를 했을 때, 두 캐릭터가 움직인 횟수의 합    
    // 게임판, 플레이어 A, 플레이어 B, 현재 라운드의 플레이어를 판단할 boolean, 움직인 총 거리의 수
    private static Result dfs(int[][] board, Point aloc, Point bloc, boolean turn, int step) {    	
    
    	Point playerA = aloc;
    	Point playerB = bloc;
    	
    	// 기저조건 1, 게임 진행 중 내 발판이 사라지면 게임을 진행하면 안됌!
    	if((turn && board[playerA.x][playerA.y] == 0 )
    			|| (!turn && board[playerB.x][playerB.y] == 0)) {    	
    		return new Result(false, step); // 현재 위치가 사라졌으므로 step은 증가하면 안된다. 따라서 step;
    	}
    	
    	// 기저조건 2에서 사용하게 될 계수!
    	int win = max; // 4방 탐색을 통과해서 결과를 판정하는데 성공했는지 판단할 역할 수행도 한다.
    	int lose = 0; //
    	
    	// 4가지 방향 모두에 대해서 dfs를 해야한다.
    	for(int d = 0 ; d< 4 ; d++) {    		    		
    		if(turn) {
    			int nx = playerA.x + dx[d];
    			int ny = playerA.y + dy[d];   
    			// 범위 체크
    			if(nx < 0 || ny < 0 || nx >= R || ny >= C ) continue;
    			// 발판 체크
    			if(board[nx][ny]==0) continue;    			
    			board[playerA.x][playerA.y] = 0; // dfs이므로 현 재귀 단계에서는 방문처리를 해주고
    			Result b = dfs(board, new Point(nx, ny), bloc, !turn, step+1); // A턴이었으므로 위치를 바꿔주고 재귀 던진다.
    			
    			// 이쪽에 왔다는 것은 기저조건에 걸려 게임이 종료되었다는 것.
    			// 따라서 게임의 승패 여부를 판정해준다.
    			if(!b.isWin) { // b가 졌다면, a는 이긴것!
    				// 양 플레이어가 최적의 플레이를 했을 때, 두 캐릭터가 움직인 횟수의 합이므로
    				win = Math.min(win, b.round);  // 상대방의 이동한 거리와 승리했을 때 중 가장 최소의 이동 거리를 비교한다 => 승리를 최대한 늦춘다.
    			}else {
    				lose = Math.max(lose, b.round); // 
    			}
    			board[playerA.x][playerA.y] = 1; // 기저조건에 걸려 재귀가 타지지 않을 경우 다시 원상 복귀 해줘야 한다.
    			
    			    			
    		}else {
    			int nx = playerB.x + dx[d];
    			int ny = playerB.y + dy[d];    			
    			if(nx < 0 || ny < 0 || nx >=R || ny >= C)continue;
    			if(board[nx][ny]==0) continue;    			
    			board[playerB.x][playerB.y] = 0;
    			Result a = dfs(board, aloc, new Point(nx, ny), !turn, step+1);
    			
    			if(!a.isWin) {
    				win = Math.min(win, a.round);    				
    			}else {
    				lose = Math.max(lose, a.round);
    			}
    			board[playerB.x][playerB.y] = 1;    			    			    			
    		}    		
    	}
    	    	
    	// 기저조건 2
        // 더 움직일 수 없을 때 현재 움직인 횟수 반환
        if(win==max && lose == 0){ // 변화가 없었다? -> 4번 케이스처럼 게임이 정상 진행되지 않았을 경우도 판정한다.
            return new Result(false, step); // 범위 제약이나, 발판의 부재로 게임이 진행되지 않았으므로 게임의 결과는 무조건 패배이고 게임은 종료시킨다.
        // 이겼을 때 최저값 반환
        }else if(win != max){ // max에 변화가 생겼다 -> 4방 탐색을 했고, 결과를 얻었는데 게임에서 이겼다
        	// min != lose        	
            return new Result(true,win); // 게임에서 이겼을 때의 결과 win(최소치)을 반환다.
        // 졌을 때 최대값 반환
        }else{ // win 계수는 변하지 않았는데 lose 계수는 변했다, -> 4방 탐색을 했고, 결과를 얻었는데 게임에서 졌다.
            return new Result(false,lose); // 게임에서 졌을 때의 lose(최대치)를 반환한다.
        }
    	
    }

    public static void main(String[] args) {
		// [[1, 1, 1], [1, 1, 1], [1, 1, 1]]	[1, 0]	[1, 2]	5
    	 int ans1 = solution(new int[][]{{1, 1, 1},{1, 1, 1},{1, 1, 1}}, new int[] {1,0}, new int[]{1,2}); // 5
    	 System.out.println(ans1);
    	
    	int ans2 = solution(new int[][] {{1,1,1},{1,0,1},{1,1,1}}, new int[] {1,0}, new int[] {1,2}); // 4
    	System.out.println(ans2);

    	int ans3 = solution(new int[][] {{1, 1, 1, 1, 1}}, new int[] {0,0}, new int[] {0,4}); // 4
    	System.out.println(ans3);

    	 int ans4 = solution(new int[][] {{1}}, new int[] {0,0}, new int[] {0,0}); // 0
     	System.out.println(ans4);
//    	
    	

    	
	}
}