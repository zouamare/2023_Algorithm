package com.java.week13;

import java.util.*;

class Solution1 {
    public int[] solution(String msg) {
    	
    	makeDict();        	
    	int[] answer = search(msg);    	
        return answer;
    }
    
    private static HashMap<String, Integer> hmap;
    private static void makeDict() {    	
    	// init
    	hmap = new HashMap<>();    	
    	int idx = 1;    
    	for(char i = 'A' ; i <= 'Z'; i++) {    		    		
    		hmap.putIfAbsent(Character.toString(i), idx++);    	    		
    	}    	
    }
    
    private static int[] search(String msg) {
    	
    	
    	ArrayList<Integer> idxList = new ArrayList<>(); // 출력결과 저장할 ArrayList

    	while(!msg.isEmpty()) {
    		
    		L:for(int i = msg.length(); i > 0 ; i--) {
    			if(hmap.containsKey(msg.substring(0, i))) {
    				
    				String key = msg.substring(0, i); // key를 추출한다.
    				String nextKey = msg.length() > i+1 ? msg.substring(i, i+1) : ""; // 다음 키 탐색
    				
    				idxList.add(hmap.get(key)); // 출력 결과 저장하고
    				
    				if(!nextKey.isEmpty() && !hmap.containsKey(key+nextKey)) {
    					hmap.put(key+nextKey, hmap.size()+1); // 없는 키 정보는 맵에 등록한다.    					
    				}
    				
    				msg = msg.substring(key.length());    				
    				
    				break L;  // 다음 반복을 위해 무조건 반복 종료  				    				
    			}
    		}
    		
    	}
    	
    	return idxList.stream().mapToInt(Integer::intValue).toArray();    	    
    	
    }
    
    public static void main(String[] args) {
    	
    	// KAKAO	[11, 1, 27, 15]
    	Solution1 sol = new Solution1();
    	int [] ans = sol.solution("KAKAO");
    	System.out.println(Arrays.toString(ans));
    	
		
	}
}