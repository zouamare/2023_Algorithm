package com.java.week8;

public class Solution3 {
	
    private static int[][] skillStack;    
    public static int solution(int[][] board, int[][] skill) {
                
        int R = board.length;
        int C = board[0].length;
        
        skillStack = new int[R+1][C+1];
        
        // 누적합 처리
        for(int[] sk : skill) {
            int fromR = sk[1]; // 1
            int fromC = sk[2]; // 1
            int toR = sk[3]+1; // 2 + 1 = 3
            int toC = sk[4]+1; // 2 + 1 = 3
                                    
            if(sk[0]==1) {// 공격                                
            	skillStack[fromR][fromC] -= sk[5];
            	skillStack[toR][toC] -= sk[5];
            	skillStack[fromR][toC] += sk[5];
            	skillStack[toR][fromC] += sk[5];                    
                
            } else {// 회복                
            	skillStack[fromR][fromC] += sk[5];
            	skillStack[toR][toC] += sk[5];
            	skillStack[fromR][toC] -= sk[5];
            	skillStack[toR][fromC] -= sk[5];

                
            }
        }
        
        System.out.println("누적합 전 결과 ----");        
        printStacks();
        System.out.println();
        
        // 누적합 진행 위에서 아래로        
        for(int c = 0 ; c< C+1; c++){
            for(int r = 1; r< R+1; r++){
            	skillStack[r][c] = skillStack[r-1][c]+skillStack[r][c];
            }
        }        
        
        /*
		 -2    0    2    0
		-2   -4    2    4
		100  -104  0    4
		 0    0    0    0
         */        
        System.out.println("누적합 후 결과 ----");
        printStacks();
        System.out.println();
        
        /* 누적합 진행 왼쪽에서 오른쪽으로 */        
        for(int r = 0 ; r< R+1; r++){
            for(int c = 1; c< C+1; c++){
            	skillStack[r][c] = skillStack[r][c-1]+skillStack[r][c];
            }
        }        

        System.out.println("누적합 후 결과2 ----");
        printStacks();
        System.out.println();

                        
        int cnt = 0;
        for(int r= 0; r < R ; r++) {
        	for(int c= 0; c<C ;c++) {
        		board[r][c] += skillStack[r][c];
        		if(board[r][c]>0) cnt++;
        	}
        }
        
        System.out.println("보드 상태----");
        printBoard(board);        
        System.out.println();
        
                
        return cnt;
    }
    
    private static void printStacks() {
    	for(int i = 0 ; i < skillStack.length; i++) {
    		for(int j = 0 ; j < skillStack[0].length; j++) {
    			System.out.print(skillStack[i][j]+"\t");
    		}
    		System.out.println();
    	}
    }
    
    
    private static void printBoard(int[][] board) {
    	for(int i = 0 ; i < board.length; i++) {
    		for(int j = 0 ; j < board[0].length; j++) {
    			System.out.print(board[i][j]+"\t");
    		}
    		System.out.println();
    	}
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// [[1,2,3],[4,5,6],[7,8,9]]	[[1,1,1,2,2,4],[1,0,0,1,1,2],[2,2,0,2,0,100]]
		int ans = solution(new int[][] {{1,2,3},{4,5,6},{7,8,9}}, new int[][] {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}});
		System.out.println("결과는 : "+ans);

	}

}
