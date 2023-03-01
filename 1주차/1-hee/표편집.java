package com.java.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

import com.java.week1.OtherAnswer3.Node;

public class 표편집 {
	
	/*
	 *  내가 시도한 풀이 전략
	 *  1. boolean[] 배열을 통해서 삭제된 노드의 상태관리를 하려고 시도
	 *  2. 배열의 인덱스를 가리키는 변수 k에 대해서 입력된 명령어 순서대로 작업을 수행하도록 설계해봄
	 *  for문, if문 등을 사용해서 로직을 구성했으나, 중간 중간 로직에 구멍이 송송 나서 테케 절반만 맞고 난리가 났음
	 *  그리고 효율성에서도 통과를 못해서 고심하다가 다른사람의 풀이를 보게 됨.
	 *  3. 명령 중 복원은 Stack으로 한다.
	 *  
	 *  
	 *  문제를 왜 못풀었을까?
	 *  다양한 풀이가 있겠지만, 효율성 테스트가 있다보니 본 문제는 최적의 해가 있어보였는데
	 *  바로 연결리스트를 직접 구현할 수 있느냐가 관건이었던 것 같음. 개념만 추상적으로 알고 있었는데
	 *  막상 직접 구현해서 해보려 하니 만들기 어려웠고 연결리스트를 직접 구현하지 못한 것이 가장 큰 패인(?) 이었던 것 같음
	 *  
	 */
	 public static class Node{
	        int pre, cur, nxt;
	        
	        public Node(int pre, int cur, int nxt) {
	            this.pre = pre;
	            this.cur = cur;
	            this.nxt = nxt;
	        }
	    }
		
		public static String solution(int n, int k, String[] cmd) {
			// 두개의 배열로 연결리스트 구현 및 관리
	        int[] pre = new int[n];
	        int[] next = new int[n];
	        
	        // 연결리스트 init
	        for(int i = 0; i < n; i++) {
	            pre[i] = i - 1;
	            next[i] = i + 1;
	        }
	        next[n - 1] = -1;
	        
	        Stack<Node> stack = new Stack<>();
	        StringBuilder sb = new StringBuilder();
	        for(int i = 0 ; i < n ; i ++) sb.append("O"); // answer String init
	        
	        for(int i = 0; i < cmd.length; i++) {
	            char c = cmd[i].charAt(0);
	            
	            if(c == 'U') { // up
	                int num = Integer.valueOf(cmd[i].substring(2)); // number
	                while(num-- > 0) {
	                    k = pre[k];
	                }
	            } else if(c == 'D') { // down
	                int num = Integer.valueOf(cmd[i].substring(2));
	                while(num-- > 0) {
	                    k = next[k];
	                }
	            } else if(c == 'C') { // delete
	                stack.push(new Node(pre[k], k, next[k])); // 복원을 위해 스택에 삭제된 노드를 저장
	                // 노드를 삭제하고, 남은 노드로 연결하는 작업
	                if(pre[k] != -1) next[pre[k]] = next[k];
	                if(next[k] != -1) pre[next[k]] = pre[k]; 
	                sb.setCharAt(k, 'X'); // 삭제된 노드의 자리는 X 처리
	                
	                if(next[k] != -1) k = next[k]; // 다음 노드가 있을 경우 다음 노드로
	                else k = pre[k]; //다음 노드가 없어 마지막 행인 경우에 바로 윗 행 선택
	            } else {
	                Node node = stack.pop(); // 복원
	                // 스택에 저장해 두었던 노드를 사용해 연결 리스트를 복원함.
	                if(node.pre != -1) next[node.pre] = node.cur;
	                if(node.nxt != -1) pre[node.nxt] = node.cur;
	                sb.setCharAt(node.cur, 'O'); // 노드의 자리를 복원, O 처리
	            }
	        }
	        return sb.toString(); // 최종 정답 String을 리턴
	    }
	
	
	
	
	
	public static void main(String[] args) {
		
		//String ans = solution(8, 2, new String[] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"});
		String ans = solution(8, 2, new String[] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"});
		//String ans = solution(6, 2, new String[] {"C","C","C","C"});

		System.out.print(ans);
		
	}

}
