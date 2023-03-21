package com.java.week3;

import java.util.Arrays;

public class Solution2 {
	
	
	/*
	 * 문제 풀이 전략
	 * -> 킹익스트라
	 * 
	 * TMI : 다익스트라로 최소값을 찾으면 될 거 같아서 무지성 다익스트라로 해보았습니다.
	 * 그런데, 다익스트라로 A -> B 의 최소값은 찾겠는데 A -> B -> C 일 때는 어떻게 구하는 지 감이 안왔던 상황
	 * 
	 * 처음엔 LinkedList를 사용해서 최소값이 갱신될 때마다 링크드 리스트에 간선을 기록해 S -> A , S -> B를 기록하려고 했습니다.
	 * 근데, 일단 이거 옳은 풀이도 아니거니와 몇번의 테스트를 통해 최적의 경로를 찾는 해법이 아니란 것을 알게 되었습니다.
	 * 
	 * 그래서 도대체 A-> B -> C를 구할지 감이 안와서 다른 사람 풀이를 참조했는데,
	 * 다익스트라를 3번 사용하면 풀 수 있는 문제였고
	 * 
	 * 각각의 시도
	 * S -> N
	 * A -> N
	 * B -> N
	 * 
	 * 끝에 얻어진 최소 비용 배열을 노드의 수 N만큼 순회하여 그중에서 최소 값을 찾아내면 되는 문제였습니다.
	 * 
	 */
		
	private static final int MAX = Integer.MAX_VALUE-10000;
	private static int V;
	private static int[][] adjList;
	
    public static int solution(int n, int s, int a, int b, int[][] fares) {
    	    	
    	V = n;
    	adjList = new int[V+1][V+1];
    	
    	for(int[] arr : fares) {
    		int from = arr[0];
    		int to = arr[1];
    		int weight = arr[2];
    		adjList[from][to] = adjList[to][from] = weight;    		
    	}
    	
    	int[] S = dijkStra(s, n);
    	int[] A = dijkStra(a, n);
    	int[] B = dijkStra(b, n);
    	
    	int min = MAX;
    	for(int i = 1; i <=n ; i++) {
    		if(min > S[i]+A[i]+B[i]) {
    			min = S[i]+A[i]+B[i];
    		}
    	}    	
        return min;
    }
        
	private static int[] dijkStra(int start, int end){
    	    	    	
		int[] D = new int[V+1];
    	boolean[] isVisited = new boolean[V+1];
    	    	
    	Arrays.fill(D, MAX);
    	D[start] = 0;
    	
    	int min, minVertex;
    	
    	for(int i = 1 ; i <= V ; i++) {
    		min = MAX;
    		minVertex = -1;
    		
    		for(int j = 1; j <= V; j ++) {
    			if(!isVisited[j] && min > D[j]) {
    				min = D[j];
    				minVertex = j;
    			}
    		}
    		
    		if(minVertex==-1) continue;
    		isVisited[minVertex] = true;

    		    		
    		for(int j = 1 ; j <= V ; j ++) {
    			if(!isVisited[j] 
    					&& adjList[j][minVertex] > 0
    					&& D[j] > D[minVertex] + adjList[j][minVertex]) {    				
    				D[j] = D[minVertex] + adjList[j][minVertex];    				
    			}
    		}
    	}
    	return D;
    }
	
	
	public static void main(String[] args) throws Exception {
		
		int n = 6;
		int s = 4;
		int a = 6;
		int b = 2;
		int[][] fares = 
			{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
		
//		int n = 7, s = 3, a = 4, b = 1;
//		int[][] fares = {
//				{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}
//		};
		
		int ans = solution(n, s, a, b, fares);
		System.out.println(ans);
		
	}
}
