import java.io.*;
import java.util.*;

/*
    과제 우선순위 시작하는과제 > 멈춘과제
    
    시간을 분단위로 바꿔서 PQ에 넣고 뽑자(정렬이 안되어있을 수 있단다)
    대기작업 자료형은 Stack쓰면된다. 어차피 새작업큐는 시간순 정렬시켜놔서 땡겨서 작업하는 모든 하위 구조에서 시간순은 보장된다.
    시뮬레이션하다가 새작업들어오면 대기 Stack에 넣고 대기시키자 넣을때 작업한 시간만큼 빼주는거 잊지말고
*/

class Solution {
    static class Job implements Comparable<Job> {
        String name;
        int startTime;
        int remainingTime;
        
        Job(String name, int startTime, int remainingTime) {
            this.name = name;
            this.startTime = startTime;
            this.remainingTime = remainingTime;
        }
        
        @Override
        public int compareTo(Job o) {
            return Integer.compare(startTime, o.startTime);
        }
    }
    
    public String[] solution(String[][] plans) {
        PriorityQueue<Job> newJobs = new PriorityQueue<>();
        Stack<Job> waitStack = new Stack<>();
        Job currentJob;
        List<String> finishJobs = new ArrayList<>();
        
        StringTokenizer st;
        
        for (String[] plan: plans) {
            String name = plan[0];
            int remainingTime = Integer.parseInt(plan[2]);
            st = new StringTokenizer(plan[1], ":");
            int startTime = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
            newJobs.offer(new Job(name, startTime, remainingTime));
        }
        
        currentJob = newJobs.poll();
        int now = currentJob.startTime;
        
        while (!newJobs.isEmpty() || !waitStack.isEmpty() || currentJob != null) {
            if (newJobs.isEmpty()) {
                finishJobs.add(currentJob.name);
                while (!waitStack.isEmpty()) {
                    finishJobs.add(waitStack.pop().name);
                }
                break;
            }
            
            /*
                시간을 하나씩 올리는건 별로 효율적이지 못한 시뮬레이션 방법이다.
                고로 now + currentJob.remainingTime 얘랑 newJobs.peek().startTime 얘를 비교하면된다.
                이렇게 처리하면 이벤트가 발생하는 시간대만 돌기때문에 시간이 압축된다.
                1. now + currentJob.remainingTime가 더 빠를때
                    - currentJob을 끝낸다.
                    - waitStack이 있을경우 currentJob으로 땡겨온다.
                    - waitStack이 없을경우 newJobs을 currentJob으로 땡겨온다.
                2. newJobs.peek().startTime가 더 빠를때
                    - currentJob.remainingTime 에 newJobs.peek().startTime - now를 빼준다.
                    - currentJob을 대기스택에 넣는다.
                    - currentJob에 newJobs.poll()
                3. 같을때
                    - currentJob을 끝낸다.
                    - currentJob = newJobs.poll();
            */
            
            if (now + currentJob.remainingTime < newJobs.peek().startTime) {
                finishJobs.add(currentJob.name);
                if (waitStack.isEmpty()) {
                    currentJob = newJobs.poll();
                    now = currentJob.startTime;
                } else {
                    now += currentJob.remainingTime;
                    currentJob = waitStack.pop();
                }
            } else if (now + currentJob.remainingTime > newJobs.peek().startTime) {
                currentJob.remainingTime -= newJobs.peek().startTime - now;
                waitStack.push(currentJob);
                currentJob = newJobs.poll();
                now = currentJob.startTime;
            } else {
                finishJobs.add(currentJob.name);
                currentJob = newJobs.poll();
                now = currentJob.startTime;
            }
        }
        
        return finishJobs.toArray(new String[0]);
    }
}