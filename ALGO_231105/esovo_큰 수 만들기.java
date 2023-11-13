import java.util.*;

class Solution {
    
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> st = new Stack<>();
        
        // 앞에서부터 앞, 뒤 숫자 비교해서 뒤가 더 큰 경우 앞자리 수 제거
        for(int i=0; i<number.length(); i++){
            if(st.isEmpty()) {
                st.push(number.charAt(i));
                continue;
            }

            if(k > 0){
                while(st.peek() < number.charAt(i)){
                    st.pop();
                    k--;
                    if(st.isEmpty() || k==0) break;
                }    
            }
            
            st.push(number.charAt(i));
        }
        
        while(!st.isEmpty()) sb.append(st.pop());
        
        // TC12는 앞자리 숫자가 뒷자리 숫자보다 작은 것이 없는 경우 뒤에서부터 차례대로 제거!
        String answer = sb.reverse().toString();
        return answer.substring(0, answer.length()-k);
    }
    
}