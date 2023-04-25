package com.java.week7;

import java.util.*;

public class Solution1 {
	
	
	private static class Point implements Comparable<Point>{
        int left, right;
        Point(int left, int right){
            this.left = left;
            this.right = right;
        }
        
        @Override
        public String toString(){
            return "Point ["+this.left+", "+this.right+"]";
        }
            
        @Override
        public int compareTo(Point o){
            return this.left - o.left;
        }
        
    }
    
    public int[] solution(int[] sequence, int k) {
    	/*
    	 * power set을 통해 풀어봤는데, 전형적인 투포인터 문제라 그런지 터졌습니다.
    	 * 근데 제가 투포인터 알고리즘을 몰라서 이번에 제대로 배웠습니다!
    	 * 
    	 * 본 문제는 투포인터를 통해 1차원 배열을 순회하여, 부분합이 k가 되는지 체크하는 문제였습니다.
    	 * 
    	 * 그러나, 동일한 부분합을 가지는 여러가지 경우의 수가 있을 수 있는데,
    	 * 이 때에는 다음의 우선순위에 따라 값을 리턴해야 합니다.
    	 * 1) 시작점과 끝 점의 차이가 최소인 경우
    	 * 2) 1)의 조건이 동일하다면 시작점의 인덱스가 더 작은 경우
    	 * 
    	 */
        
    	
    	// 투포인터를 위한 기본 세팅
        int left = 0; 
        int right = 0;
        int N = sequence.length;
        int sum = sequence[left]; // 부분합 init; 
        
        ArrayList<Point> points = new ArrayList<>(); // 부분합이 k인 경우를 저장하기 위한 ArrayList;
        
        while(true){            
            if(sum==k){
            	// right나 left를 < N 까지 증가시키므로 right=N인 경우가 오기도 해서,
            	// left나 right가 N인 경우에는 값을 담지 않도록 조건식을 걸어봤습니다.
                if(left < N && right < N){
                    points.add(new Point(left, right));
                }
            }            
            if(left == N && right == N) break; // 종료 조건
            
            if(sum <= k && right < N){ // right만 overflow하지 않도록 조건식 설정
            	
                right++; // 포인터 이동 후의 값을 더해주어야 하기 때문에 right를 먼저 증가시켜준다.
                
                if(right < N ) sum += sequence[right]; // 조건식을 통해 index Exception 방지
                
            }else {
            	
            	// 조건식을 통해 index Exception 방지 
                if(left < N) sum -= sequence[left]; // 포인터를 이동하기전 미리 값을 뺀 후 이동해줘야 하기 때문에 이 경우는 left에 대한 증가가 나중에 이루어진다.
                left++; 
            }                        
        }
                
        int diff = Integer.MAX_VALUE; // 최소 gap 비교를 위해 초기 값 init
        int from = -1; // index로 나올 수 없는 값으로 init
        int to = -1;
        
        Collections.sort(points); // 정렬을 통해 자연스레 오름차 순으로 탐색할 수 있도록 함.
        
        for(Point p : points){
            if(diff > p.right - p.left){ 
            	// 이 조건식을 통해 동일한 gap이 반복되는 
            	// 3번 테스트 케이스에 대해 대응이 가능하며 자연스럽게 오름차순으로 갱신이 된다.
            	
                diff = p.right - p.left;
                from = p.left;
                to = p.right;
                
            }
        }
        
        
        int[] answer = {from, to};
        return answer;
    }

}
