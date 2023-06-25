package com.java.week12;

import java.util.*;

public class Solution1 {
	
	private static final int JAKAD = 65536;	
	
	public static int solution(String str1, String str2) {
        		
		ArrayList<String> word1 = makeTokens(str1);				
		ArrayList<String> word2 = makeTokens(str2);		
				
		if(word1.size()==0 && word2.size()==0) return JAKAD;
		
		HashMap<String, Integer> hmap = new HashMap<>();				
		
		countToMap(word1, hmap);
		countToMap(word2, hmap);
				
        int count =(int) hmap.entrySet().stream().filter(m -> m.getValue() >= 2).count();
        return (int) (1.0 * count / hmap.size() * JAKAD);
        
    }

	private static void countToMap(ArrayList<String> word, HashMap<String, Integer> hmap) {
		for (String str: word) {			
			if(hmap.containsKey(str)) {
				hmap.replace(str, hmap.get(str)+1);
			}else {
				hmap.put(str, 1);				
			}
        }
	}
	
	private static ArrayList<String> makeTokens(String word) {		
		ArrayList<String> arr = new ArrayList<>();
		String str = word.toLowerCase();
		StringBuilder sb = new StringBuilder();
		sb.append(str.charAt(0));
		
		for(int i =1; i < word.length(); i ++) {
			sb.append(str.charAt(i));
			if(isAlpha(sb.toString())) addToken(sb.toString(), arr); // 이부분이 핵심이었다...
			sb.deleteCharAt(0);
		}				
		
		return arr;		
	}
	
	private static void addToken(String string, ArrayList<String> arr) {
		int index = 0;
		String token = string + String.valueOf(index);
		while(arr.contains(token)) {
			token = string + String.valueOf(++index);
		}
		arr.add(token);				
	}

	private static boolean isAlpha(String str) {
		for(char ch : str.toCharArray()) {
			if(ch < 'a' || ch > 'z' )
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		int ans1 = solution("FRANCE", "french");
		int ans2 = solution("handshake", "shake hands");
		int ans3 = solution("aa1+aa2", "AAAA12");
		int ans4 = solution("E=M*C^2", "e=m*c^2");
		int ans5 = solution("abab", "baba");

		System.out.println(ans1);
		System.out.println(ans2);
		System.out.println(ans3);
		System.out.println(ans4);
		System.out.println(ans5);
				
	}

}
