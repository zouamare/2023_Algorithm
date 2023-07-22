package com.java.week15;

import java.util.*;

class Solution2 {
	
	private static final int SIZE = 2500;
	private static int[] parents;
	private static String[] values;
		
	//union find 구조
	private static void make() {
		parents = new int[SIZE+1];
		values = new String[SIZE+1];
		for(int i = 1 ; i <= SIZE ; i++) {
			parents[i] = i;
			values[i] = "";
		}
	}
	
	private static int find(int a) {
		if(parents[a] == a) return a;
		else return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}

    // 2차원 좌표를 1차원 배열 인덱스로 컨버팅 해주는 함수
	private static int getConvertedIndex(int x, int y) {
		int result = 50 * (x-1);
		return result + y;
	}
	
	// solution
    public String[] solution(String[] commands) {        
    	
    	List<String> result = new ArrayList<>();
    	
    	make(); // index를 관리할 parents, 값을 관리할 value init!    	
    	for(String s : commands) {
    		StringTokenizer st = new StringTokenizer(s);
    		centralProcess(st, result);
    	}            
    	
    	int size = result.size();
    	String[] answer = result.stream().toArray(s -> new String[size]);    	
        return answer;
    }
    
    // 
    private static void centralProcess(StringTokenizer st, List<String> result) {
    	String header = st.nextToken(); // 모든 명령어는 동작이 첫 번째 토큰, 즉 헤더 부분에 있음!    	
    	// 헤더를 기준으로 분기한다.
    	if("UPDATE".equals(header)) {
    		if(st.countTokens() == 2) { // update value value
    			String value1 = st.nextToken();
    			String value2 = st.nextToken();
    			updateAll(value1, value2);

    		}else if(st.countTokens() == 3) { // update r c value
    			int r = Integer.parseInt(st.nextToken());
    			int c = Integer.parseInt(st.nextToken());
    			String value = st.nextToken();
    			
    			updateCell(r, c, value);
    		}	    		
    		
    	}else if("MERGE".equals(header)) { // merge r1 c1 r2 c2
    		int r1 = Integer.parseInt(st.nextToken());
    		int c1 = Integer.parseInt(st.nextToken());
    		int r2 = Integer.parseInt(st.nextToken());
    		int c2 = Integer.parseInt(st.nextToken());
    		merge(r1, c1, r2, c2);	    			    			    		
    		
    	}else if("UNMERGE".equals(header)) {
    		int r = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());	    		
    		unMerge(r, c);

    	}else if("PRINT".equals(header)) {
    		int r = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());    		
    		print(r,c, result);
    	}    	   	
    }
    
    // UPDATE r c value
    private static void updateCell(int r, int c, String value){
    	int cIdx = getConvertedIndex(r, c); // 1차원 배열에서의 index를 가져온다.
    	values[find(cIdx)] = value; // 값 대입       
    }
    
    // UPDATE value value
    private static void updateAll(String before, String after){
    	for(int i = 1 ; i <= SIZE; i++) {
    		if(before.equals(values[i])) {
    			values[i] = after;
    		}
    	}        
    }
    
    // MERGE r1 c1 r2 c2
    private static void merge(int r1, int c1, int r2, int c2){
    	int cIdx1 = getConvertedIndex(r1, c1);
    	int cIdx2 = getConvertedIndex(r2, c2);    	
    	int rootA = find(cIdx1);
    	int rootB = find(cIdx2);
    	
    	if(rootA == rootB) return; // 값이 같으면 종료
    	
    	String rootValue = values[rootA].isEmpty() ? values[rootB] : values[rootA];
    	values[rootA] = "";
    	values[rootB] = "";
    	union(rootA, rootB);
    	
    	values[rootA] = rootValue; 
    }

    // UNMERGE r c
    private static void unMerge(int r, int c){
        int cIdx = getConvertedIndex(r, c);
        int root = find(cIdx);
        String rootValue = values[root]; // 값을 찾고
        values[root] = ""; // 루트를 초기화
        values[cIdx] = rootValue; // 머지 풀 셀 값만 남기고
        
        // 병합된 셀을 모두 찾고, 루트와 같으면, 리스트에 저장
        // why? for문에서 찾자마자 바꿔버리면 연결이 끊겨서, 모든 연결을 제거하지 못함
        List<Integer> deleteList = new ArrayList<>();
        for(int i = 1; i <=SIZE; i++) {
        	if(find(i)==root) {
        		deleteList.add(i);
        	}
        }
        // 끊을 연결에 대해서만 초기화
        for(int idx : deleteList) {
        	parents[idx] = idx; // 처음 인덱스로 초기화
        }        
    }
    
    // PRINT r c
    private static void print(int r, int c, List<String> list){
    	int cIdx = getConvertedIndex(r, c);  
    	int root = find(cIdx);
        list.add(values[root].isEmpty() ? "EMPTY" : values[root]);
    }
    
    //////
    public static void main(String[] args) {
    	Solution2 sol2 = new Solution2();
		String[] ans = sol2.solution(
				new String[]{"UPDATE 1 1 menu", 
							 "UPDATE 1 2 category", 
							 "UPDATE 2 1 bibimbap", 
							 "UPDATE 2 2 korean", 
							 "UPDATE 2 3 rice", 
							 "UPDATE 3 1 ramyeon", 
							 "UPDATE 3 2 korean", 
							 "UPDATE 3 3 noodle", 
							 "UPDATE 3 4 instant", 
							 "UPDATE 4 1 pasta", 
							 "UPDATE 4 2 italian", 
							 "UPDATE 4 3 noodle", 
							 "MERGE 1 2 1 3", 
							 "MERGE 1 3 1 4", 
							 "UPDATE korean hansik", 
							 "UPDATE 1 3 group", 
							 "UNMERGE 1 4", 
							 "PRINT 1 3", 
							 "PRINT 1 4"});		
		
		System.out.println(Arrays.toString(ans));

	}
    
    
}