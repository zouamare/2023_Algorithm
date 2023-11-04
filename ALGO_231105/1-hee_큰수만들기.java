import java.util.*;

class Solution {
    public String solution(String number, int k) {
        
        // https://born2bedeveloper.tistory.com/27
        
        StringBuilder sb = new StringBuilder();
        int len = number.length() - k; // 최대로 담을 수 있는 숫자의 개수
        int start = 0; // 시작 인덱스
        int size = number.length(); 
        
        // 시작 인덱스를 기준으로 전체 순회 여부를 판단하고
        // len을 기준으로 StringBuilder의 Capacity를 체크해준다.
        // 즉, 반복문이 끝까지 순회 완료 했는지 && StringBuilde의 용량이 가득찼는지를 체크
        while(start < size && sb.length() != len){ 
            // 아래의 중첩 반복문에서 
            // start라는 시작 인덱는 최대 값을 찾으면 갱신 되는데
            // 따라서, 이를 고려하여 중첩 반복문의 out of range 오류를 방지하기 위해
            // 아래와 같이 종료 index를 동적으로 계산해준다.
            int l = k + sb.length() + 1; 
            int max = 0;

            for(int j = start; j < l; j++){
                int num = number.charAt(j) - '0'; // type casting
                if(max < num){
                    max = num; // 최대 값을 갱신해준다
                    start = j+1; // 시작 인덱스도 갱신한다.
                }
            }
            sb.append(max); // answer append            
        }
        
        String answer = sb.toString();        
        return answer; // 결과 산출
    }    
}