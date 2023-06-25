package com.java.week4;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution1 {
	
	
	private static int[][] map;
	private static int R, C;
	
	static class Point{
		int x, y, times; // x, y, 횟수
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Point(int x, int y, int times) {
			super();
			this.x = x;
			this.y = y;
			this.times = times;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", times=" + times + "]";
		}			
	}
	
    public static int solution(String[] board) {    	
    	R = board.length;
    	C = board[0].length();
    	
    	map = new int[R][C];    	    	
    	// R : 시작
    	// G : 도착점
    	// D : 장애물
    	
    	int[] start = new int[2];
    	
    	for(int r = 0 ; r < R; r++) {    		
    		String line = board[r];
    		for(int c = 0 ; c < C ; c++) {
    			map[r][c] = transToNumber(line.charAt(c)); 
    			if(map[r][c] == 1) {
    				start[0] = r;
    				start[1] = c;
    			}
    		}
    	}
    	    	
        return bfs(new Point(start[0], start[1]));
    }
    
	private static int transToNumber(char c) {
		switch (c) {
		case 'R':
			return 1; // 시작
		case 'G':
			return 2; // 목표
		case 'D':
			return 3; // 장애물
		default:
			return 0;
		}
	}
    
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0,0, -1, 1};    
    private static int bfs(Point start) {
    	
    	boolean[][] isVisited = new boolean[R][C];
    	Queue<Point> que = new ArrayDeque<>();
    	que.offer(new Point(start.x, start.y, 0));
    	isVisited[start.x][start.y] = true;
    	
    	while(!que.isEmpty()) {    		
    		Point cur = que.poll();    		
    		// 기저조건
    		if(map[cur.x][cur.y]==2) {
    			return cur.times;
    		}    		
    		    		
    		for(int d = 0; d<4 ; d++) {
    			int nx = cur.x;
    			int ny = cur.y;
    			
    			while(true) {
    				nx += dx[d];
    				ny += dy[d];
    				
    				if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
    					nx -= dx[d];
    					ny -= dy[d];
    					break;
    				}
    				
    				if(map[nx][ny]==3) {
    					nx -= dx[d];
    					ny -= dy[d];
    					break;
    				}    						
    			}
    			
    			if(isVisited[nx][ny]) continue;
    			isVisited[nx][ny] = true;
    			que.offer(new Point(nx, ny, cur.times+1));    			
    			
    		}
    	}
    	
    	return -1;
    }
    


	public static void main(String[] args) {

		solution(new String[] {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."});
		solution(new String[]{".D.R", "....", ".G..", "...D"});

	}

}
