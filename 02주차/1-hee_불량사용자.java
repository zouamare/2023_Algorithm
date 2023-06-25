package com.java.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Answer1_불량사용자_조원희 {

	/*
	 * 풀이 방법 요약
	 * 1. NextPermutation으로 모든 경우를 구함 ( -> 풀다보니 조합으로 했어야 되는거 같아보이는데 뭐가 맞는지 잘 모르겠.... )
	 * 2. 2중 반복문을 돌면서 사용자 id와 밴먹은 아이디를 각각 대조해보고 밴된 유저를 마킹함 + 동시에 밴먹은 사용자 수를 셈함
	 * 3. 셈한 카운트 계수를 기준으로 최대 밴 먹은 사용자 수 (MAX_SIZE)를 선정합니다.
	 * 4. 밴먹은 유저들의 조합을 담은 전역 HashSet(results)에 담아 관리하는데, 
	 * 5. 3번에 셈하는 계수가 갱신되면 clear() 해줘서 날린 후 현재 사용자 아이디 조합을 등록합니다. 만약 동일하다면 그냥 아이디 조합만 등록합니다.
	 * 6. 1~5를 모두 수행한  HashSet의 사이즈를 리턴하여 경우의 수를 계산완료.
	 * 
	 * 이런 무식한(?) 풀이를 생간한 이유 : user_id 사이즈가 8개로 매우 매우 작음
	 * 그래서 무지성 공략...
	 * 혹시몰라 찾아보니 다른 사람들 풀이가 훨씬 나은듯... 이렇게는 풀지 마십쇼 ㅠㅠ
	 * 
	 */
	
	private static int MAX_SIZE = -1; // 경우의 수의 최대값
	private static HashSet<HashSet<String>> results;
    public static int solution(String[] user_id, String[] banned_id) {

    	// 전역 변수 초기화
    	MAX_SIZE = -1;
    	results = new HashSet<>();
    	
    	// banned_id에 대한 index 배열
    	int[] indexs = new int[user_id.length];
    	for(int i = 0 ; i < user_id.length; i ++) indexs[i] = i;    	
    	String[] changedUserId = new String[user_id.length];
    	
    	// step1. index를 마구마구 섞어서 경우의 수를 생성한다.
    	do {
    		// 섞인 index를 기준으로 새로운 id 배열을 만든다.    		
    		for(int i = 0 ; i < user_id.length; i ++) 
    			changedUserId[i] = user_id[indexs[i]];
    		
    		int cnt = 0; // 각 경우의 수마다 밴으로 확인되는 유저를 셈하는 변수
    		boolean[] isSelected1 = new boolean[changedUserId.length]; // 사용자 id에 대한 체크배열
    		boolean[] isSelected2 = new boolean[banned_id.length]; // 밴 먹은 id에 대한 체크배열
    		
    		// 다해본다!.
    		for(int i = 0 ; i < user_id.length ; i ++) {    			
    			if(isSelected1[i]) continue; // 이미 골라진건 패스!
    			
    			for(int j = 0 ; j < banned_id.length; j ++) {
    				
    				if(isSelected2[j]) continue; // 이미 고른건 패스!
    				
    				if(isMatch(changedUserId[i], banned_id[j])) { // 두 문자열이 서로 같으면?
    					isSelected1[i] = true; // user_id 마킹하고
    					isSelected2[j] = true; // banned_id 마킹하고
    					++cnt; // 카운트 상승
    					break; // 현재 반복 종료.
    				}
    			}    			
    		}
    		
    		
    		if(cnt > MAX_SIZE) { // 최댓값 갱신?
    			results.clear();
    			MAX_SIZE = cnt; // 최댓값 갱신    			        		        		
    			HashSet<String> list = getSelectedStringList(changedUserId, isSelected1);
    			results.add(list);    			
    		}else if (MAX_SIZE == cnt) {
    			HashSet<String> list = getSelectedStringList(changedUserId, isSelected1);
    			results.add(list);    			

    		}
    		
    	}while(np(indexs));
    	
        return results.size();
    }
    
    private static HashSet<String> getSelectedStringList(String[] strings, boolean[] isSelected) {    	
    	HashSet<String> result = new HashSet<>();
    	for(int i = 0 ; i < isSelected.length; i ++) {
    		if(isSelected[i]) result.add(strings[i]);
    	}    	
    	return result;
    }
        
    // 와일드 카드에 대해 모든 경우의 수를 계산해줄 next permutation
    private static boolean np(int[] numbers) {
    	int N = numbers.length;
    	int i = N-1;
    	while(i>0 && numbers[i-1] >= numbers[i]) --i;
    	if(i==0) return false; // 다음 순열을 만들 수 있는 가장 큰 순열의 상태
    	
    	int j = N-1;
    	while(numbers[i-1] >= numbers[j]) --j;    	
    	swap(numbers, i-1, j);
    	
    	int k = N-1;
    	while(i<k) swap(numbers, i++, k--);    	
    	return true;    	
    }
    
    private static void swap(int[] numbers, int i, int j) {
    	int temp = numbers[i];
    	numbers[i] = numbers[j];
    	numbers[j] = temp;
    }
        
    // 유저 아이디가 와일드 카드 문자열과 일치하는지 검사하는 메서드
    public static boolean isMatch(String userId, String compareId) {    	
    	if(userId.length() != compareId.length()) return false; // 길이가 다르면 false;    	
    	for(int i = 0 ; i < userId.length(); i ++) {
    		if(compareId.charAt(i)=='*') continue; // 와일드 카드면 패스
    		if(userId.charAt(i)!=compareId.charAt(i)) return false; // 같지 않으면 false;
    	}
    	return true; // 통과했으면 true;
    }
    
    public static void main(String[] args) {
    	// ["frodo", "fradi", "crodo", "abc123", "frodoc"]	["fr*d*", "abc1**"]
    	// ["frodo", "fradi", "crodo", "abc123", "frodoc"]	["*rodo", "*rodo", "******"]
    	// case 1
    	String[] user_id1 = new String[] {"frodo", "fradi", "crodo", "abc123"};
    	String[] banned_id1 = new String[] {"fr*d*", "abc1**"}; // 2

    	// case 2
    	String[] user_id2 = new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"};
    	String[] banned_id2 = new String[] {"*rodo", "*rodo", "******"}; // 2

    	// case 3
    	String[] user_id3 = new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"};
    	String[] banned_id3 = new String[] {"fr*d*", "*rodo", "******", "******"}; // 3

    	
    	int ans = solution(user_id3, banned_id3);
    	System.out.println(ans);
//    	System.out.println(results.toString());
    	

	}

}
