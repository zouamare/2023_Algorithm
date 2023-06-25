package com.java.week1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 두큐합같게만들기 {
	public static int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();
        
        Queue<Integer> que1 = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();
        
        for(int i : queue1) que1.add(i);
        for(int i : queue2) que2.add(i);
        
        // 여기가 키 포인트
        int end = (que1.size() + que2.size()) * 2; // 두 큐를 주고 받으면서 나올 수 있는 최악의 경우의 수        
        int cnt = 0;
        
        while(sum1 != sum2) { // 두 큐가 같을 때까지
        	cnt++;
        	int p;
        	if(sum1>sum2) {
        		p = que1.poll();
        		sum1 -= p;
        		sum2 += p;
        		que2.offer(p);        		
        	}else {
        		p = que2.poll();
        		sum1 += p;
        		sum2 -= p;
        		que1.offer(p);
        	}
        	if(cnt > end) return -1;  // 역치를 넘어서, 큐를 주고 받는다면 두 큐의 합을 같게 할 수 없는 경우이므로 -1을 리턴     	
        }        
        return cnt;
    }
	
	public static void main(String[] args) {
		// [3, 2, 7, 2]	[4, 6, 5, 1] >> 2
		// [1, 2, 1, 2]	[1, 10, 1, 2] >> 7
		// [1, 1]	[1, 5]	>> -1
		
		System.out.println(solution(new int[] {3,2,7,2}, new int[] {4,6,5,1}));
		System.out.println(solution(new int[] {1,2,1,2}, new int[] {1,10,1,2}));
		System.out.println(solution(new int[] {1,1}, new int[] {1,5}));
		
	}

}
