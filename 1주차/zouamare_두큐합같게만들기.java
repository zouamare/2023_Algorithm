package coding_test.AlgoY2023.M03.D01;

import java.util.ArrayDeque;
import java.util.Queue;
/*
* 1) Brute Force 방식 => Best!!
* - 처음에는 DFS 방식인 줄 알았으나.. 이를 구현하기 위해서는 Queue를 깊은 복사해야 했는데 아무리 봐도 그건 아닌 것 같았다.
* - 따라서 인터넷을 살짝 참고 했을 때 '단순한' 방법이라는 설명 하나만 보고 "아 내가 너무 어렵게 생각했나보다.." 라고 생각했다.
* - 정말 단순히 값을 동일하게 맞추기 위해서는 부족한 쪽에 넘치는 부분을 더하면 되는 것이었는데!!
* - 그러나 홀수 일 경우 구할 수 없다는 것은 미처 생각 못한 부분이라서 문제 풀이할 때 수학적으로 생각하는 버릇을 더 들여야 겠다고 생각했다.
* */

public class zouamare_두큐합같게만들기 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 2, 7, 2}, new int[]{4, 6, 5, 1}));
    }

    static int count;
    public static int solution(int[] queue1, int[] queue2) {
        long totalVal = 0;
        long aVal = 0, bVal = 0;
        count = Integer.MAX_VALUE;
        Queue<Integer> queueA = new ArrayDeque<>();
        Queue<Integer> queueB = new ArrayDeque<>();
        for(int i = 0; i < queue1.length; i++){
            totalVal += queue1[i];
            aVal += queue1[i];
            queueA.add(queue1[i]);
        }
        for(int i = 0; i < queue2.length; i++){
            totalVal += queue2[i];
            bVal += queue2[i];
            queueB.add(queue2[i]);
        }

        if(totalVal % 2 != 0){
            return -1;  // 합이 홀수이면 아무리 옮겨도 두 값이 합쳐지지 않음
        }

        int count = 0;
        int maxCount = (queue1.length + queue2.length) * 2;
        while(aVal != bVal){
            if(++count > maxCount){  // queue1 + queue2 의 경우보다는 더 많이 옮길 수 있으므로 해당 값에 2를 곱해줌!
                return -1;
            }
            if(aVal > bVal){    // bVal이 더 작은 경우
                if(queueA.isEmpty()){
                    return -1;
                }
                int val = queueA.poll();
                queueB.offer(val);
                aVal -= val;
                bVal += val;
            }
            else{   // aVal이 더 작은 경우
                if(queueB.isEmpty()){
                    return -1;
                }
                int val = queueB.poll();
                queueA.offer(val);
                bVal -= val;
                aVal += val;
            }
        }

        return count;
    }
}
