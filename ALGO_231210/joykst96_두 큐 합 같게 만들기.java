// https://school.programmers.co.kr/learn/courses/30/lessons/118667
import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        long sum1 = 0;
        long sum2 = 0;
        for (int i: queue1) {
            sum1 += i;
            q1.offer(i);
        }
        for (int i: queue2) {
            sum2 += i;
            q2.offer(i);
        }
        int count = -1;
        int end = (queue1.length + queue2.length) * 2;
        while (++count < end) {
            if (sum1 == sum2) return count;
            if (sum1 > sum2) {
                int poped = q1.poll();
                sum1 -= poped;
                sum2 += poped;
                q2.offer(poped);
            } else {
                int poped = q2.poll();
                sum1 += poped;
                sum2 -= poped;
                q1.offer(poped);
            }
        }
        return -1;
    }
}