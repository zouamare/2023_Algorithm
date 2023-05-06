package com.java.week8;

import java.util.*;

class Solution4 {
	
	private static int[] dx = {1,-1, 0, 0};
	private static int[] dy = {0,0, 1, -1};
	private static int answer;
	private static int max = 10_000_000;
	
	
	static class PlayResult {
		boolean isWin;
		int round;
		
		public PlayResult(boolean isWin, int round){
			this.isWin = isWin;
			this.round = round;
		}		
	}
	
    public static int solution(int[][] board, int[] aloc, int[] bloc) {
        
    	R = board.length;
    	C = board[0].length;
    	
        PlayResult result = dfs(board,aloc,bloc,1,0);
        return result.round;
    }
    
    
    private static int R, C;
    
    private static PlayResult dfs(int[][] board, int[] aloc, int[] bloc, int turn, int move) {
    	
    	int ax = aloc[0];
    	int ay = aloc[1];
    	
    	int bx = bloc[0];
    	int by = bloc[1];
    	
    	if((turn > 0 && board[ax][ay] == 0 )
    			|| (turn < 0 && board[bx][by] == 0)) {    	
    		return new PlayResult(false, move);
    	}
    	
    	
    	int win = max;
    	int lose = 0;
    	
    	for(int d = 0 ; d< 4 ; d++) {
    		    		
    		if(turn > 0) {
    			int nx = ax + dx[d];
    			int ny = ay + dy[d];
    			
    			if(nx < 0 || ny < 0 || nx >= R || ny >= C ) continue;
    			if(board[nx][ny]==0) continue;
    			
    			board[ax][ay] = 0;
    			PlayResult b = dfs(board, new int[] {nx, ny}, bloc, -turn, move+1);
    			
    			if(!b.isWin) {
    				win = Math.min(win, b.round);
    			}else {
    				lose = Math.max(lose, b.round);
    			}
    			board[ax][ay] = 1;
    			    			
    		}else {
    			int nx = bx + dx[d];
    			int ny = by + dy[d];
    			
    			if(nx < 0 || ny < 0 || nx >=R || ny >= C)continue;
    			if(board[nx][ny]==0) continue;
    			
    			board[bx][by] = 0;
    			PlayResult a = dfs(board, aloc, new int[] {nx, ny}, -turn, move+1);
    			
    			if(!a.isWin) {
    				win = Math.min(win, a.round);    				
    			}else {
    				lose = Math.max(lose, a.round);
    			}
    			board[bx][by] = 1;    			    			
    			
    		}    		
    	}
    	
    	
        //더 움직일 수 없을 때 현재 움직인 횟수 반환
        if(win==max && lose == 0){
            return new PlayResult(false,move);
        // 이겼을 때 최저값 반환
        }else if(win != max){
            return new PlayResult(true,win);
        // 졌을 때 최대값 반환
        }else{
            return new PlayResult(false,lose);
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