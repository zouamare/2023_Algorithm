package com.java.week4;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Soultion5 {
		
	private static boolean[][] isVisisted;
	private static int R, C;
	private static int[][] map;
	
    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

    	
        R = m;
        C = n;
        
        map = new int[R][];
        isVisisted = new boolean[R][C];
        
        for(int r = 0 ; r< R ; r++) {
        	map[r] = picture[r].clone();
        }
        
        for(int r = 0 ; r< R ; r++) {
        	for(int c = 0 ; c <C; c++) {
        		if(!isVisisted[r][c] &&
        				map[r][c] !=0        				
        				) {
        			
        			int result = bfs(new int[] {r, c}, map[r][c]);
        			maxSizeOfOneArea= Math.max(maxSizeOfOneArea, result);
        			if(result>0) numberOfArea++;
        			
        		}
        	}
        }
        
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0, -1, 1};
    private static int bfs(int[] pos, int no) {
    	
    	Queue<int[]> que = new ArrayDeque<>();
    	isVisisted[pos[0]][pos[1]] = true;
    	que.offer(pos);
    	
    	int size = 0;
    	while(!que.isEmpty()) {
    		int[] cur = que.poll();
    		size++;
    		
    		for(int d = 0; d < 4; d++) {
    			int nx = cur[0] + dx[d];
    			int ny = cur[1] + dy[d];
    			
    			if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
    			if(isVisisted[nx][ny]) continue;
    			if(map[nx][ny]==no) {
    				isVisisted[nx][ny] = true;
    				que.offer(new int[] {nx, ny});
    			}    			    			
    		}
    		
    	}
    	
    	return size;
    }
    
    public static void main(String[] args) {
    	
    	int[] ans = solution(6, 4, new int[][] {
    		{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}
    	});
    	
    	System.out.println(Arrays.toString(ans));
		
	}
	
	

}
