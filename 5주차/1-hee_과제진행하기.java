package com.java.week5;

import java.util.*;

public class Solultion1 {
	
	/*
	 * [["korean", "11:40", "30"], ["english", "12:10", "20"], ["math", "12:30", "40"]]
	 */
	
	static class Subject implements Comparable<Subject>{
		String name;
		int start, playtime, leftTime;
		
		public Subject(String name, int start, int playtime, int leftTime) {
			this.name = name;
			this.start = start;
			this.playtime = playtime;
			this.leftTime = leftTime;
		}
		
		public Subject(String name, int start, int playtime) {
			this.name = name;
			this.start = start;
			this.playtime = playtime;
		}
		
		@Override
		public String toString() {
			return "Subject [name=" + name + ", start=" + start + ", playtime=" + playtime + ", leftTime=" + leftTime
					+ "]";
		}

		@Override
		public int compareTo(Subject o) {
			return this.start - o.start;
		}				
	}
	
    public static String[] solution(String[][] plans) {
        
        List<String> result = new ArrayList<>();
        Queue<Subject> remain = new PriorityQueue<>();
        Deque<Subject> task = new ArrayDeque<>();

    	
        for(String[] strs : plans) {
        	Subject sbj = 
        			new Subject(strs[0],
        					getStartTime(strs[1]), 
        					getPlayTime(strs[2]));        	
        	remain.offer(sbj);
        }        
        
        
        // 키포인트
        // 시작시간(pq의 가장 첫 요소의 start 시간)을 설정한다.        
        int currTime = remain.peek().start; 
        task.add(remain.poll()); // deque에 작업을 하나 넣는다. 
        
        while(!remain.isEmpty()) {
        	Subject next;    
        	
        	// 시작한 뒤 일을 했는데 다음 업무로 인해 일을 바꿔야할 경우
        	if( remain.peek().start < currTime + task.peekLast().playtime) {
        		
        		next = remain.poll(); // 교체하고,
                task.peekLast().playtime -= next.start - currTime; // 최대한 업무시간을 뺀 후
        		currTime = next.start; // 시간을 초기화하고
        		task.add(next); // 덱에 업무를 추가 함, peekLast를 통해 마지막 원소 접근 용이하기 위함
        		
        	}else { // 뒷 순서 생각 안해도 그냥 업무 수행 가능하다면?
        		currTime += task.peekLast().playtime; // 소요시간만큼 더하고
        		result.add(task.pollLast().name); // 이 업무는 종료되었으므로 결과로 넣는다.
        	}
        	
            if(task.isEmpty()) { // 대기중인업무가 없는 경우 (윗 else 절에서 비워진 경우)
                currTime = remain.peek().start; // 다음 업무로 시간을 땡기고
                task.add(remain.poll()); // 대기열에 업무를 넣는다.
            } 	
        }
        
        while(!task.isEmpty()) { // 잔여 업무에 대해 최신순서대로 처리한다.
        	result.add(task.pollLast().name);
        }                
              
    	String[] answer = new String[plans.length];                
    	for(int i = 0 ; i < answer.length;i ++) {
    		answer[i] = result.get(i);
    	}

        return answer;
    }
    
    
    private static int getStartTime(String time) {
    	StringTokenizer st = new StringTokenizer(time, ":");
    	int hour = Integer.parseInt(st.nextToken());
    	int min = Integer.parseInt(st.nextToken());
    	int totalTime = hour*60 + min;    	    	
    	return totalTime;    	
    }
    
    private static int getPlayTime(String time) {
    	return Integer.parseInt(time);
    }
    
    
    public static void main(String[] args) {
		String[] ans = solution(new String[][] {
			{"korean", "11:40", "30"}, 
			{"english", "12:10", "20"}, 
			{"math", "12:30", "40"}
		});
				
		String[] ans2 = solution(new String[][] {
			{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}
		});
		
		String[] ans3 = solution(new String[][] {
			{"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}
		});
		
		System.out.println(Arrays.toString(ans));
		System.out.println(Arrays.toString(ans2));
		System.out.println(Arrays.toString(ans3));
		
	}

}
