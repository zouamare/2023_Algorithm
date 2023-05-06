package com.java.week8;

public class Solution2 {
    public static long solution(int cap, int n, int[] deliveries, int[] pickups){        
    	
    	int divCapacity = 0,  // 배달 용량
    			pickCapacity = 0; // 수거 용량
    	long answer = 0; // 이동한 총 거리!
    	
    	for(int i = n-1; i >= 0 ; i--) { // 배열을 뒤부터 순회한다!
    		if(deliveries[i] !=0 || pickups[i] != 0) { // 배열에 0보다 큰 값이 있다 = 일거리가 있다!
    			int cnt = 0; // 매번 초기화를 통해서 이 지점에 일하러 온 횟수를 셈한다!
    			while(divCapacity < deliveries[i] || pickCapacity < pickups[i]){ 
    				// 각각의 Capacity를 기준으로 이 조건이 true라면, 여기로 다시 와야한다는 의미!
    				cnt++; // 온 횟수를 셈하고
    				divCapacity += cap; // 각각의 capacity를 증가시킨다!
    				pickCapacity += cap;    				
    			}
    			// 만약 용량이 부족했다면 while문을 통해서 용량 증가를, 용량이 충분했다면 while문을 돌지 않고 이곳으로 바로 내려온다.
    			// 그러므로 현재 위치에서의 일을 모두 처리하기만 하면 된다 >> 뺄셈을 통해 일을 처리했음을 계산해준다!
    			divCapacity -= deliveries[i];
    			pickCapacity -= pickups[i];
    			// 만약 while문을 통해 cnt가 증가했다면, 현재지점 i에 오기 위해 거리를 사용한 것이므로 거리를 계산해준다.
    			answer += (i+1) * cnt * 2; // 현재 거리 x 이동 횟수 x 2 (왕복이므로!)    			    			
    		}
    	}
    	    	
         return answer;
    }
    
    public static void main(String[] args) {

    	long ans = solution(2, 5, new int[] {0,1,0,3,4}, new int[]{2,0,2,0,1});
    	System.out.println(ans);
	}

}
