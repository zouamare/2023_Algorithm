package com.java.week8;

import java.util.*;

public class Solution5 {
	
    public static int solution(int m, int n, String[] board) {
    	
    	R = m;
    	C = n;
    	map = new char[R][C];    	
    	removeQueue = new LinkedList<>();
    	    	
    	// init
    	for(int i = 0 ; i < R; i ++) {
    		for(int j = 0 ; j < C; j ++) {
    			map[i][j] = board[i].charAt(j);
    		}    		
    	}
    	
    	
    	int ans = 0;
    	
    	while(true) {    		
        	for(int r = 0 ; r<R ; r++) {
        		for(int c= 0 ; c<C; c++) {
        			if(map[r][c]=='.') continue;
        			searchBlock(new Block(r, c));
        		}
        	}
        	
        	int cnt = 0;
        	while(!removeQueue.isEmpty()) {
        		Block b= removeQueue.poll();
        		if(map[b.r][b.c]=='.') continue;
        		map[b.r][b.c] = '.';
        		++cnt;
						
        	}
        	
        	if(cnt==0) break;
        	ans += cnt;
        	
        	for(int c = 0 ; c< C ; c++) {
        		Stack<Character> stk  = new Stack<>();
        		for(int r = 0; r<R ; r++) {
        			if(map[r][c]!='.') stk.push(map[r][c]);
        			map[r][c] = '.';
        		}
        		int idx = R-1;
        		while(!stk.isEmpty()) {
        			map[idx--][c] = stk.pop();
        		}
        	}
    	}
        return ans;
    }
    
    // 블록 정보를 담을 클래스
    private static class Block {
    	int r, c;
    	Block(int r, int c){
    		this.r = r;
    		this.c = c;
    	}
		@Override
		public String toString() {
			return "Block [r=" + r + ", c=" + c + "]";
		}    	
    }
        
    // 블록 정보를 저장할 배열
    private static char[][] map;
    
    // 블록 제거를 위해 사용할 큐
    private static Queue<Block> removeQueue;
    
    // 4개인지 파악하는 메서드
    private static int R, C;
    private static int[] dx = new int[]{1, 0, 1};
    private static int[] dy = new int[]{0, 1, 1};
    private static boolean searchBlock(Block b) {
    	
    	ArrayList<Block> bList = new ArrayList<>();
    	bList.add(b);
    	char value = map[b.r][b.c];
    	
    	for(int d = 0 ; d < 3; d++) {
    		int nx = b.r + dx[d];
    		int ny = b.c + dy[d];
    		if(nx < 0 || ny < 0 || nx >= R || ny >= C) return false; // 범위 벗어나면, 그 포인트는 유효하지 않으므로
    		if(map[nx][ny] != value) return false; // 값이 동일하지 않으면 탈락...
    		bList.add(new Block(nx, ny));    		
    	}
    	
    	
    	for(Block block : bList) {
    		removeQueue.offer(block);
    	}
    	return true;    	
    }
    
	public static void main(String[] args) {
		
		int m1 = 6, n1 = 6;
		String[] board1 = new String[] { "TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
		
		int ans1 = solution(m1, n1, board1); // 15
		System.out.println(ans1);
		
		
		
		int m2= 4, n2 = 5;
		String[] board2 = new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"};
		int ans2 = solution(m2, n2, board2); // 14
		System.out.println(ans2);
		

	}

}
