package week8_9;

public class esovo_프렌즈4블록 {

	public static void main(String[] args) {
		int a = solution(4, 5, new String[] { "CCBDE", "AAADE", "AAABF", "CCBBF" });
		System.out.println(a);
	}

	static int[] dr = { 0, 0, 1, 1 };
	static int[] dc = { 0, 1, 1, 0 };

	public static int solution(int m, int n, String[] board) {
		int answer = 0;

		char[][] map = new char[m][n];
		for (int i = 0; i < m; i++) {
			map[i] = board[i].toCharArray();
		}

		boolean isContinue = true;
		boolean[][] cur = new boolean[m][n];

		while (isContinue) {
			cur = new boolean[m][n];
			isContinue = false;
			
			// 돌면서 2x2 모양 확인
			for (int i = 0; i < m - 1; i++) {
				for (int j = 0; j < n - 1; j++) {
					if(map[i][j]=='0') continue;
					for (int k = 0; k < 4; k++) {
						if (map[i][j] == map[i + dr[k]][j + dc[k]]) {
							if (k == 3) {
								isContinue = true;
								for (int l = 0; l < 4; l++) {
									cur[i + dr[l]][j + dc[l]] = true;
								}
							}
						} 
						else break;
					}
				}
			}

			// 블록 터뜨리기
			for(int i=0; i<m; i++) {
				for(int j=0; j<n; j++) {
					if(cur[i][j]) map[i][j] = '0';
				}
			}
			
			// 빈 공간 채우기
			for (int c = 0; c < n; c++) {
				int r = m - 1;
				while (r > 0) {
					if (map[r][c] == '0') {
						int dx = r - 1;
						while (dx > 0 && map[dx][c] == '0') dx--;
						map[r][c] = map[dx][c];
						map[dx][c] = '0';
					}
					r--;
				}
			}
		}

		// 빈 공간 = 터진 블록이므로 개수 세기
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == '0')
					answer++;
			}
		}
		return answer;
	}
}
