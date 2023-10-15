package com.java.week11;

import java.util.*;

public class Solution3 {
	
	char[][] map;
    static class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString(){
            return "Point = [ x = "+this.x+", y = "+this.y+"]";
        }
    }
    
    LinkedList<Character> lklist;
    HashMap<Character, Point[]> hmap;    

    public String solution(int m, int n, String[] board) {        
        
        StringBuilder sb = new StringBuilder();
        lklist = new LinkedList<Character>();
        hmap = new HashMap<Character, Point[]>();
        
        this.map = new char[m][n];
        
        for(int i = 0 ; i < m ; i ++){
            for(int j = 0 ; j < n ; j++){
                
                char c = board[i].charAt(j);
                this.map[i][j] = c;
                
                if(c != '.' && c != '*'){
                    
                    if(!lklist.contains(c)){
                        lklist.add(c);
                        hmap.put(c, new Point[2]);                        
                        // 첫번째 타일
                        hmap.get(c)[0] = new Point(i, j);
                        
                    } else {
                        // 두번째 타일
                        hmap.get(c)[1] = new Point(i, j);                        
                    }
                }
            }
        }
        
        Collections.sort(lklist);
        
        int idx = 0;
        
        while(lklist.size() != 0){
            if(canDelete(lklist.get(idx))){//지울 수 있는 타일인가?
                char curChar = lklist.remove(idx);
                sb.append(curChar);
                deleteChar(curChar);
                idx = 0;                
            }else {
                idx++;
                if(idx == lklist.size()) return "IMPOSSIBLE";
            }
        }

        return sb.toString();
    }
    
    private void deleteChar(char c){
        map[hmap.get(c)[0].x][hmap.get(c)[0].y] = '.';
        map[hmap.get(c)[1].x][hmap.get(c)[1].y] = '.';        
    }
    
    
    private boolean canDelete(Character c){
        
        // 무조건 두개의 타일이므로 문자 하나만 알아도 두개의 좌표를 꺼낼 수 있다.
        int r1 = hmap.get(c)[0].x;
        int c1 = hmap.get(c)[0].y;
        int r2 = hmap.get(c)[1].x;
        int c2 = hmap.get(c)[1].y;
        
        // 열 값을 기준으로 타일의 위치를 판단!
        if(c1 < c2){
            // 두 번째 타일이 첫번째 타일보다 오른쪽에 위치
            // 방향 1 체크
            if(columnCheck(c1, c2, r1, c) && rowCheck(r1, r2, c2, c)) return true;            
            // 방향 2 체크
            if(rowCheck(r1,r2, c1, c) && columnCheck(c1, c2, r2, c)) return true;
        }else {
            // 두 번째 타일이 첫번째 타일보다 왼쪽에 위치
            
            // 방향 3 체크
            if(rowCheck(r1, r2, c2, c) && columnCheck(c2, c1, r1, c)) return true;
            // 방향 4 체크
            if(columnCheck(c2, c1, r2, c) && rowCheck(r1, r2, c1, c)) return true;

        }
        
        return false;        
        
    }
    
    private boolean rowCheck(int r1, int r2, int c, Character a){
        for(int i = r1; i < r2+1 ; i++){
            if(map[i][c] != '.' && map[i][c] != a) return false;
        }
        return true;
    }
    
    private boolean columnCheck(int c1, int c2, int r, Character a){
        for(int i = c1; i < c2+1 ; i++){
            if(map[r][i] != '.' && map[r][i] != a) return false;
        }
        return true;
    }


	public static void main(String[] args) {
    	/*
	    	3	3	["DBA", "C*A", "CDB"]	"ABCD"
			2	4	["NRYN", "ARYA"]	"RYAN"
			4	4	[".ZI.", "M.**", "MZU.", ".IU."]	"MUZI"
			2	2	["AB", "BA"]	"IMPOSSIBLE"
		*/
    	Solution3 sol = new Solution3();
    	String ans1 = sol.solution(3, 3, new String[]{"DBA", "C*A", "CDB"});
    	System.out.println(ans1);
    	
	}
}