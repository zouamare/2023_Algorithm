package week8_9;

public class esovo_파괴되지않은건물 {
	
	public static void main(String[] args) {
		int a = solution(new int[][] {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}}, new int[][] {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}});
		System.out.println(a);
	}
	
	public static int solution(int[][] board, int[][] skill) {
	        int answer = 0;
	        
	        int r = board.length;
	        int c = board[0].length;
	        int[][] arr = new int[r+1][c+1];
	        
	        // 누적합 구하기
	        for(int i=0; i<skill.length; i++){
	            int r1 = skill[i][1];
	            int c1 = skill[i][2];
	            int r2 = skill[i][3];
	            int c2 = skill[i][4];
	            int degree = skill[i][5];
	            
							// 적인 경우 음수로 바꾸기
	            if(skill[i][0] == 1) degree = (-1) * degree;
	            arr[r1][c1] += degree;
	            arr[r2+1][c2+1] += degree;
	            arr[r1][c2+1] -= degree;
	            arr[r2+1][c1] -= degree; 
	        }
	        
	        // 아래쪽으로 누적합
	        for(int j=0; j<c; j++){
	            for(int i=1; i<r; i++){
	                arr[i][j] += arr[i-1][j];
	            }
	        }
	        
	        // 오른쪽으로 누적합
	        for(int i=0; i<r; i++){
	            for(int j=1; j<c; j++){
	                arr[i][j] += arr[i][j-1];
	            }
	        }
	        
	        // 파괴되지 않은 건물 개수 구하기
	        for(int i=0; i<r; i++){
	            for(int j=0; j<c; j++){
	                board[i][j] += arr[i][j];
	                if(board[i][j] > 0) answer++;
	            }
	        }
	        
	        return answer;
	    }
}

