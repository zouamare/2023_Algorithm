package day0301;

import java.util.LinkedList;
import java.util.Queue;

public class esovo_두큐합같게만들기 {
	
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        // 각 큐의 합 구하기
        long sum1 = 0, sum2=0;
        for(int i=0; i<queue1.length; i++){
            sum1 += queue1[i];
            q1.offer(queue1[i]);
        }
        
        for(int i=0; i<queue2.length; i++){
            sum2 += queue2[i];
            q2.offer(queue2[i]);
        }
    
        while(true){
            if(sum1 > sum2){
                sum1 -= q1.peek();
                sum2 += q1.peek();
                q2.add(q1.poll());
                answer++;
            }
            else if(sum1 < sum2){
                sum2 -= q2.peek();
                sum1 += q2.peek();
                q1.add(q2.poll());
                answer++;
            }
            else break;
            
            // 시간초과 해결 위해 추가!
            if(queue1.length*4 < answer){
                answer = -1;
                break;
            }
        }
        
        return answer;
    }
}