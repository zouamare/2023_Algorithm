import java.util.*;

class Solution {        
    static class Number implements Comparable<Number>{
        int n; // 숫자
        public Number(int n){
            this.n = n;
        }
        @Override
        public String toString(){
            return "Number = [ n = " + this.n + "]";
        }
        @Override
        public int compareTo(Number o){
            String so1 = String.valueOf(this.n);
            String so2 = String.valueOf(o.n);            
            int standard = (so2+so1).compareTo(so1+so2);            
            return standard;
        }
    }
        
    public String solution(int[] numbers) {
                
        // pque를 이용한 풀이
        PriorityQueue<Number> pque = new PriorityQueue<>();        
        for(int i : numbers){
            Number n = new Number(i);
            pque.offer(n);
        }        
        StringBuilder sb = new StringBuilder();        
        while(!pque.isEmpty()){
            sb.append(pque.poll().n);
        }        
        String answer = sb.toString();        
        return answer.charAt(0)=='0' ? "0" : answer;
        
        /* 성능
        MAX : 테스트 3 〉	통과 (331.67ms, 294MB)
        MIN : 테스트 15 〉	통과 (1.74ms, 65.1MB)        
        */
               
        // short 풀이
        // https://bellog.tistory.com/170
//         String[] arr = new String[numbers.length];
//         for(int i = 0 ; i < arr.length ; i++){
//             arr[i] = String.valueOf(numbers[i]);
//         }        
//         Arrays.sort(arr, (o1, o2) -> (o2+o1).compareTo(o1+o2));        
//         if(arr[0].equals("0")) return "0";        
//         StringBuilder sb = new StringBuilder();
//         for(int i = 0 ; i < arr.length ; i++){
//             sb.append(arr[i]);
//         }                
//         String answer = sb.toString();        
//         return answer;
        
        /* 성능        
        MAX : 테스트 3 〉	통과 (178.77ms, 125MB)
        MIN : 테스트 15 〉	통과 (1.73ms, 77.6MB)
        */
    }
}