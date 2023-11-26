import java.util.*;

class Solution {
    
    static class Task {
        int priority;
        int location;

        Task(int priority, int location) {
            this.priority = priority;
            this.location = location;
        }

        boolean isRunnable(int priority) {
            return this.priority == priority;
        }

    }
    
    public int solution(int[] priorities, int location) {
        Queue<Task> wait = new ArrayDeque<>();
        PriorityQueue<Integer> order = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < priorities.length; ++i) {
            wait.offer(new Task(priorities[i], i));
            order.offer(priorities[i]);
        }
        int done = 0;
        while (!wait.isEmpty()) {
            Task current = wait.poll();
            if (current.isRunnable(order.peek())) {
                if (current.location == location) return ++done;
                order.poll();
                ++done;
            } else {
                wait.offer(current);
            }
        }
        return -1;
    }
}