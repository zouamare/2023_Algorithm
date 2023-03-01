package day0301;

import java.util.Stack;

public class esovo_표편집 {

	public static void main(String[] args) {
		String s = solution(8, 2, new String[] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"});
		System.out.println(s);
	}
	
	public static String solution(int n, int k, String[] cmd) {
		boolean[] isDeleted = new boolean[n];
		Stack<Integer> stack = new Stack();
		
		for(int i=0; i<cmd.length; i++) {
			String[] st = cmd[i].split(" ");
			int cnt = 0;
			switch(st[0]) {
			case "U": // 위에 있는 행 선택
				for(int j=k-1; j>=0; j--) {
					if(isDeleted[j]) continue;
					cnt++;
					if(Integer.parseInt(st[1]) == cnt) {
						k = j;
						break;
					}
				}
				break;
			case "D": // 아래에 있는 행 선택
				for(int j=k+1; j<n; j++) {
					if(isDeleted[j]) continue;
					cnt++;
					if(Integer.parseInt(st[1]) == cnt) {
						k = j;
						break;
					}
				}
				break;
			case "C": // 현재 선택된 행 삭제 후 아래 행 선택. 마지막 행인 경우 바로 윗 행 선택
				isDeleted[k] = true;
				stack.push(k);
				if(k == n-1) { // 마지막 행인 경우....ㅠ_ㅠ isDeleted 고려해야함
					for(int j=k-1; j>=0; j--) {
						if(isDeleted[j]) continue;
						cnt++;
						if(cnt == 1) {
							k = j;
							break;
						}
					}
					break;
				}
				for(int j=k+1; j<n; j++) {
					if(isDeleted[j]) continue;
					cnt++;
					if(cnt == 1) {
						k = j;
						break;
					}
				}
				break;
			case "Z": // 가장 최근에 삭제한 행 복구. 현재 선택된 행은 그대로
				isDeleted[stack.pop()] = false;
				break;
			}
		}
		
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
        	sb.append(isDeleted[i] ? 'X' : 'O');
        }
        return sb.toString();
    }
}
