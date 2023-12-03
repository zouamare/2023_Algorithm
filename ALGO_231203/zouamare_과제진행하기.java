package coding_test.AlgoY2023.M12.D02;

import java.util.Stack;
import java.util.Arrays;

public class 과제진행하기 {
    public String[] solution(String[][] plans) {
        Subject[] subjects = new Subject[plans.length];

        for(int i = 0; i < plans.length; i++){
            subjects[i] = new Subject(plans[i][0], timeToInt(plans[i][1]), Integer.parseInt(plans[i][2]));
        }

        Arrays.sort(subjects);

        String[] answer = new String[subjects.length];
        Stack<Subject> stack = new Stack<>();
        Subject now = subjects[0];
        int idx = 1;
        int ansIdx = 0;
        boolean flag = true;        // 진행해야 하는 과제의 맨 끝까지 갔는지
        int time = now.startTime;

        for(int i = 1; i < subjects.length; i++){
            if(time + now.playTime <= subjects[i].startTime){
                answer[ansIdx++] = now.name;
                time += now.playTime;
                while(time < subjects[i].startTime && !stack.isEmpty()){
                    // 여기서 계속 뽑는다
                    Subject sub = stack.pop();
                    if(time + sub.playTime <= subjects[i].startTime){
                        answer[ansIdx++] = sub.name;
                        time = time + sub.playTime;
                    }
                    else{
                        stack.add(new Subject(sub.name, sub.startTime, sub.playTime - (subjects[i].startTime - time)));
                        time = subjects[i].startTime;
                    }
                }
            }
            else{
                stack.add(new Subject(now.name, now.startTime, now.playTime - (subjects[i].startTime - time)));
                time = subjects[i].startTime;
            }
            now = subjects[i];
            time = now.startTime;
        }

        answer[ansIdx++] = now.name;

        while(!stack.isEmpty()){
            answer[ansIdx++] = stack.pop().name;
        }

        return answer;
    }

    private int timeToInt(String time){
        String[] result = time.split(":");
        return Integer.parseInt(result[0]) * 60 + Integer.parseInt(result[1]);
    }

    class Subject implements Comparable<Subject> {
        String name;
        int startTime;
        int playTime;

        public Subject(String name, int startTime, int playTime){
            this.name = name;
            this.startTime = startTime;
            this.playTime = playTime;
        }

        public int compareTo(Subject s){
            return this.startTime - s.startTime;
        }
    }
}
