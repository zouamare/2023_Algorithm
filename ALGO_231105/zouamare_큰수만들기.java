package coding_test.AlgoY2023.M11.D04;

import java.util.Stack;

public class 큰수만들기
{
    // 시간 초과 줄이는 법
    // 비교 타입 Integer => Character
    // ArrayDeque 두개를 사용하지 않고 스택을 이용하여 현재 값과 이전의 값을 비교하여 이전의 값을 k만큼 지워준다.
    public static void main(String[] args) {
        System.out.println(solution("1231234", 3));
    }
    public static String solution(String number, int k) {
       char[] numbers = new char[number.length() - k];
       Stack<Character> stack = new Stack<>();

       for(int i = 0; i < number.length(); i++){
           char c = number.charAt(i);
           while(!stack.isEmpty() && stack.peek() < c && k-- > 0)   stack.pop();
           stack.push(c);
       }

       for(int i = 0; i < numbers.length; i++){
           numbers[i] = stack.get(i);
       }

       return new String(numbers);
    }
}
