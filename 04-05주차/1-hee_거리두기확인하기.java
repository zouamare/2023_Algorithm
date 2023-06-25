package com.java.week4;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution3 {
	

	/*
	 * 
	 * [["POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"], 
	 * ["POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"], 
	 * ["PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"], 
	 * ["OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"], 
	 * ["PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"]]	
	 * 
	 * [1, 0, 1, 1, 1]
	 * 
	 *  5 * 5 fix
	 */
	
	static class Point {
		int x, y;
		
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}		
	}
			 	
	
    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        for(int i = 0 ; i < places.length; i ++) {
        	String[] place = places[i];
        	Queue<Point> que = new ArrayDeque<>(); // 사람 있는 포지션 저장하기 위한 큐
        	
        	
        	boolean isOk = true;
        	for(int j = 0 ; j < places[i].length; j++) {        		        		
        		String line = places[i][j];
        		int length = places[i][j].length();
        		for(int k = 0 ; k < length; k++) {
        			
        			if(line.charAt(k)=='P') {
        				if(!bfs(new Point(j, k), place)) {
        					isOk = false;
        				}
        			}        			
        		}        		
        	}
        	answer[i] = isOk? 1 : 0;
        	
        }        
                
        return answer;
    }
    
    
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0,0, -1, 1};
    private static boolean bfs(Point p, String[] arr) {
    	Queue<Point>  que = new ArrayDeque<>();
    	que.offer(new Point(p.x, p.y));
    	
    	while(!que.isEmpty()) {
    		Point pos = que.poll();
    		
    		for(int k = 0 ; k < 4 ; k ++) {
    			int nx = pos.x + dx[k];
    			int ny = pos.y + dy[k];
    			
    			if(nx < 0 || ny < 0 || nx >= 5 || ny >= 5 
    					|| nx==p.x && ny == p.y) continue;
    			
    			int d = Math.abs(nx - p.x) + Math.abs(ny - p.y);
    			
    			if(arr[nx].charAt(ny)=='P' && d <= 2) {
    				return false;
    			}else if(arr[nx].charAt(ny)=='O' && d < 2) {
    				que.offer(new Point(nx, ny));
    			}
    		
    		}
    	}
    	return true;
    }




	public static void main(String[] args) {
		
		int[] ans = solution(				
					new String[][] {					
						{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
						{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, 
						{"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, 
						{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, 
						{"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}	
					}				
				);
		
		System.out.println(Arrays.toString(ans));
		
		
		
	}
	
	

}
