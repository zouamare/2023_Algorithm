import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(String operation : operations){
            String[] str = operation.split(" ");
            switch(str[0]){
                case "I": // 큐에 숫자 삽입
                    maxHeap.add(Integer.parseInt(str[1]));
                    minHeap.add(Integer.parseInt(str[1]));
                    break;
                case "D": // 숫자 삭제
                    if(Integer.parseInt(str[1]) == 1){
                        if(maxHeap.isEmpty()) continue;
                        minHeap.remove(maxHeap.poll());
                    } 
                    else{
                        if(minHeap.isEmpty()) continue;
                        maxHeap.remove(minHeap.poll());
                    }
                    break;
            }
        }

        int[] answer = {maxHeap.isEmpty() ? 0 : maxHeap.poll(), minHeap.isEmpty() ? 0 : minHeap.poll()};
        return answer;
    }
}