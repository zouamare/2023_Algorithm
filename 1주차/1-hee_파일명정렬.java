package com.java.week1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 대소문자 구분없이 사전순 정렬을 했는가?
숫자부분의 길이가 5가 넘어가면 나머지 부분은 Tail로 처리했는가?
Tail로 처리를 하다가, 다시 숫자가 나왔을때도 그대로 Tail로 처리되는가?
 */

/*
 *  테스트케이스 1~3 은 맞았는데, 그 이후부터는 도저히 감이 안와서 답지를 보게 됨
 *  정렬에 PQ를 사용했는데, PQ 정렬 기준을 설정하지 않아 모두 return 0인 경우에는
 *  입력된 순서를 보장할 수 없는 문제를 발견함
 *  그래서 PQ를 사용하지 않은 방법으로 문제를 풀어야 한다는 것을 알게 되었고,
 *  아이디어가 생각나지 않아서 다른 사람의 풀이를 참고하게 됨
 *  
 *  문제를 왜 못풀었을까?
 *  1. 파일명을 기준에 따라 나누는 것은 몇가지 테스트 케이스를 돌려봤을 때 잘 작동함을 확인했음
 * 		그런데 아마도 PQ의 정렬방식이 모두 우선순위가 같을 때 입력순서로 출력되는 것을 보장하지 않는 것 같아서 이부분이 패인이었던 것 같음
 *  2. 파일이름에서 숫자영역을 탐지하여 파일명을 분할할 때, 폴더명이 숫자로 끝나는 경우에 대한 예외처리?? 를 못했음
 *  
 */

public class 파일명정렬{
	
	static class FileNameObject {
		String head = null;
		String number = null;
		String tail = null;
		
		@Override
		public String toString() {
			return "FileNameObject [head=" + head + ", number=" + number + ", tail=" + tail + "]";
		}
		
		public String getFullName() {
			return this.head+this.number+this.tail;
		}
						
	}
		
    public static String[] solution(String[] files) {
    	
    	Arrays.sort(files, (o1, o2)->{
    		FileNameObject fno1 = getFileNameObject(o1);
    		FileNameObject fno2 = getFileNameObject(o2);
    		
    		int headCmpIdx = fno1.head.compareTo(fno2.head);
    		int numCmpIdx = Integer.parseInt(fno1.number)-Integer.parseInt(fno2.number);
    		
    		if(headCmpIdx==0) {
    			if(numCmpIdx==0) {
    				return 0;
    			}
    			return numCmpIdx;
    		}
    		return headCmpIdx;
    	});
    	    	
    	return files;
    }
    
    private static FileNameObject getFileNameObject(String s) {
    	FileNameObject fno = new FileNameObject();
    	boolean state = false;
    	int startIdx = -1;
    	
    	for(int i = 0 ; i < s.length(); i ++) {
    		if(!state && isNumber(s.charAt(i))) {
    			fno.head = s.substring(0, i).toLowerCase();
    			startIdx = i;
    			state = true;
    			if(startIdx == s.length()-1) {
    				fno.number = s.substring(startIdx);
    			}
    		}else if(state && !isNumber(s.charAt(i))) {    			
    			i = i-startIdx <= 5? i : startIdx+5;
    			fno.number = s.substring(startIdx, i);
    			fno.tail = s.substring(i);
    			break;
    		}    		
    	}
    	
    	// 위 조건에서 못잡는 파일 명의 경우 파일명 세팅을 위해서 필요한 로직    	
    	// ex) file015 << 이런거 잡을 때
    	// 위 테케의 경우 fno.head에 file 까지만 들어가 있을 것, 015도 넣어줘야하는데 그 때 사용될 로직
    	if(fno.number==null) {
    		int i = s.length();
    		i = i-startIdx <= 5? i:startIdx+5;
    		fno.number = s.substring(startIdx, i);
    		fno.tail = s.substring(i);
    	}
    	    	
    	return fno;
    }
    	
    
    private static boolean isNumber(char c) {
    	return c>='0' && c<='9';
    }

	public static void main(String[] args) {
		
		 String[] answer = solution(new String[] {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"});
			// "img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"    	
		 System.out.println(Arrays.toString(answer));


	}
	
}
