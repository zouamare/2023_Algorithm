package coding_test.AlgoY2023.M11.D11;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class 이중우선순위큐 {
    // 두개의 PQ (민힙, 멕스힙) 만들어서 각각의 MAP으로 관리
    // 그리고 서로의 맵 보면서 만약에 빠져있으면 나도 빼기

    public static void main(String[] args) {
        solution(new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"});
    }
    public static int[] solution(String[] operations) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        Map<Integer, Integer> maxMap = new HashMap<>();
        Map<Integer, Integer> minMap = new HashMap<>();

        for(String oper : operations){
            String[] data = oper.split(" ");
            if(data[0].equals("I")){
                // INSERT
                maxHeap.offer(Integer.parseInt(data[1]));
                minHeap.offer(Integer.parseInt(data[1]));
            }
            else{
                // DELETE
                if(data[1].equals("1")){
                    // DELETE MAX
                    while(!maxHeap.isEmpty() && maxMap.getOrDefault(maxHeap.peek(), 0) < minMap.getOrDefault(maxHeap.peek(), 0)) {
                        int deletedMAX = maxHeap.poll();
                        maxMap.put(deletedMAX, maxMap.getOrDefault(deletedMAX, 0) + 1);
                    }
                    if(!maxHeap.isEmpty()){
                        int deletedMAX = maxHeap.poll();
                        maxMap.put(deletedMAX, maxMap.getOrDefault(deletedMAX, 0) + 1);
                    }
                }
                else{
                    // DELETE MIN
                    while(!minHeap.isEmpty() && minMap.getOrDefault(minHeap.peek(), 0) < maxMap.getOrDefault(minHeap.peek(), 0)) {
                        int deletedMIN = minHeap.poll();
                        minMap.put(deletedMIN, minMap.getOrDefault(deletedMIN, 0) + 1);
                    }
                    if(!minHeap.isEmpty()){
                        int deletedMIN = minHeap.poll();
                        minMap.put(deletedMIN, minMap.getOrDefault(deletedMIN, 0) + 1);
                    }
                }
            }
        }

        while(!maxHeap.isEmpty() && maxMap.getOrDefault(maxHeap.peek(), 0) < minMap.getOrDefault(maxHeap.peek(), 0)) {
            int deletedMAX = maxHeap.poll();
            maxMap.put(deletedMAX, maxMap.getOrDefault(deletedMAX, 0) + 1);
        }
        while(!minHeap.isEmpty() && minMap.getOrDefault(minHeap.peek(), 0) < maxMap.getOrDefault(minHeap.peek(), 0)) {
            int deletedMIN = minHeap.poll();
            minMap.put(deletedMIN, minMap.getOrDefault(deletedMIN, 0) + 1);
        }

        if(!maxHeap.isEmpty() && !minHeap.isEmpty()){
            return new int[]{maxHeap.peek(), minHeap.peek()};
        }
        return new int[]{ 0, 0};
    }
}
