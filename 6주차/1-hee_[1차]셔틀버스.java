package com.java.week5;

import java.util.*;

public class Solution3 {
		
	// 풀이전략
	/*
	 * 1. 주어진 n, t, m을 기준으로 버스 배열을 만든다.
	 * 2. timetable을 기준으로 대소비교를 통해 각 버스에 탑승 가능한 승객을 태운다.
	 * 3. 채워진 배열을 순회하면서 콘이 탑승할 수 있는 가장 늦은 시간을 대소비교 한다.
	 */
	
	// 버스 클래스
	static class Bus{
		int time; // 24시간 기준이므로 분으로 계산해서 넣을 예정
		int capacity; // 버스의 수용인원
		int[] seats; // 승객들이 앉을 시트, 정수 배열로 관리하고 시간을 기록한다.
		
		public Bus(int time, int capacity, int[] seats) {
			super();
			this.time = time;
			this.capacity = capacity;
			this.seats = seats;
		}

		@Override
		public String toString() {
			return "Bus [time=" + time + ", capacity=" + capacity + ", seats=" + Arrays.toString(seats) + "]";
		}		
	}
	
	private static class Person{
		int arriveTime;
		
		public Person(int arriveTime) {
			this.arriveTime = arriveTime;
		}

		@Override
		public String toString() {
			return "Person [arriveTime=" + arriveTime + "]";
		}		
	}
	
	private static Bus[] busTable; // 버스를 담을 배열	
	private static PriorityQueue<Person> waitQueue; // 대기열
	private static final int START_TIME = 60*9; // 09시 출발!
	
	public static String solution(int n, int t, int m, String[] timetable) {
		
		busTable = new Bus[n];
		
		// bus init
		for(int i = 0 ; i < n ; i ++) {
			int busTime = START_TIME + i*t;		
			int[] seats = new int[m];
			Arrays.fill(seats, -1); // 00시에 탈수도 있으므로 -1로 초기화			
			busTable[i] = new Bus(busTime, m, seats);
		}
		
		waitQueue = new PriorityQueue<Person>(new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o1.arriveTime - o2.arriveTime;
			}
		});
		
		StringTokenizer st = null;		
		
		// 사람들 대기열에 넣기
		for(String time:timetable) {
			st = new StringTokenizer(time, ":");
			int arriveTime = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
			waitQueue.offer(new Person(arriveTime));			
		}
				
		// 버스 지나가유
		L:for(int i = 0 ; i < n; i ++) {			
			Bus bus = busTable[i];					
			
			for(int k = 0 ; k < bus.capacity; k++) { // 버스의 수용량만큼 				
				if(waitQueue.isEmpty()) break L; // 다탔으면 그만~;							
				if(waitQueue.peek().arriveTime<=bus.time) {
					Person p = waitQueue.poll(); // 버스 탑승한다.
					bus.seats[k] = p.arriveTime;
				}					
			}								
		}
		
		// 콘님 나가신다 자리 비켜라~
		int max = -1;				
		for(int i = 0; i < n; i++) { // 버스배열을 돌면서~
			Bus bus = busTable[i];			
			for(int j = 0; j < bus.capacity; j++) {
				if(bus.seats[j]==-1) { // 빈자리가 있네?
					max = Math.max(max, bus.time); // 이게 최선인가?
				}else { // 없네,
					max = Math.max(max, bus.seats[j]-1); // 제일 늦는 크루보타 1분만 일찍 타야지
				}
			}			
		}
		
		// 결과는 정수형이므로, 시간 문자열로 변환한다.
		StringBuffer sb = new StringBuffer();
		
		// 시간 처리
		if(max/60>9) { // 시간이 두자리인가?
			sb.append(max/60).append(":");
		}else {
			sb.append(0).append(max/60).append(":");
		}
		
		// 분 처리
		if(max%60 > 9) { // 분은 두자리인가?
			sb.append(max%60);
		}else {
			sb.append(0).append(max%60);			
		}
				
        return sb.toString(); // 결과리턴~
    }
	
	public static void main(String[] args) {
		
		int n1= 1, t1 = 1, m1 = 5;		
		String ans = solution(n1, t1, m1, new String[] {"08:00", "08:01", "08:02", "08:03"});
		System.out.println(ans); // 09:00
		
		int n2= 2, t2 = 10, m2 = 2;
		String ans2 = solution(n2, t2, m2, new String[] {"09:10", "09:09", "08:00"});
		System.out.println(ans2); // 09:09
		

		int n3 = 2, t3 = 1, m3 = 2;
		String ans3 = solution(n3, t3, m3, new String[] {"09:00", "09:00", "09:00", "09:00"});
		System.out.println(ans3); // 08:59

		int n4 = 1, t4 = 5, m4 = 1;
		String ans4 = solution(n4, t4, m4, new String[] {"00:01", "00:01", "00:01", "00:01", "00:01"});
		System.out.println(ans4); // 00:00;
		
		int n5 = 1, t5 = 1, m5 = 1;
		String ans5 = solution(n5, t5, m5, new String[] {"23:59"});
		System.out.println(ans5); // 09:00;
		
		
		int n6 = 10, t6 = 60, m6 = 45;
		String ans6 = solution(n6, t6, m6, 
				new String[] 
						{"23:59","23:59", "23:59", "23:59", "23:59", "23:59", 
						"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", 
						"23:59", "23:59", "23:59", "23:59"});
		System.out.println(ans6); // 18:00;
		
		
		
		
	}

}
