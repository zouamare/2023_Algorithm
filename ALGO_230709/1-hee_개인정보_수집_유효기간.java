package com.java.week13;

import java.util.*;

public class Solution2 {
	
	private static class LogInfo{
		int no, saveTime;
		String type;
		
		public LogInfo(int no, int saveTime, String type) {
			this.no = no;
			this.saveTime = saveTime;
			this.type = type;
		}
		
	}
	
	private static int Today = -1;
	private static HashMap<String, Integer> termMap;
	public int[] solution(String today, String[] terms, String[] privacies) {
		
		Today = transTime(today);		
		ArrayList<LogInfo> logList = new ArrayList<>();
		termMap = new HashMap<>();
		
		// 로그 정보 받고
		int idx = 1;
		for(String s : privacies) {			
			StringTokenizer st = new StringTokenizer(s);									
			int saveTime = transTime(st.nextToken());
			String type = st.nextToken();
			
			LogInfo li = new LogInfo(idx++, saveTime, type);
			logList.add(li);
		}
		
		// 기한 설정
		for(String s : terms) {
			StringTokenizer st = new StringTokenizer(s);								
			String type = st.nextToken();
			int duration = Integer.parseInt(st.nextToken())*MONTH;
			termMap.put(type, duration);
		}
		
		ArrayList<Integer> noList = new ArrayList<>();
		
		for(LogInfo li : logList) {
			int calcTime = li.saveTime + termMap.getOrDefault(li.type, 0);
			if(Today >= calcTime) {
				noList.add(li.no);
			}
		}
						
        return noList.stream().mapToInt(Integer::intValue).toArray();
    }
	
	
	// 날짜 변환용 함수
	private static final int YEAR = 12*28;
	private static final int MONTH = 28;	
	private static int transTime(String date) {
		
		StringTokenizer st = new StringTokenizer(date, ".");
		
		int sumDate = 
				getNumber(st.nextToken())*YEAR 
				+getNumber(st.nextToken())*MONTH
				+getNumber(st.nextToken());		
		
		return sumDate;
	}
	
	private static int getNumber(String st) {
		return Integer.parseInt(st);
	}
	 
	 public static void main(String[] args) {
		 
		 Solution2 sol = new Solution2();
		 
		 int[] ans = sol.solution("2022.05.19",
				 new String[]{"A 6", "B 12", "C 3"},
				 new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"}); // [1, 3]
		 		 
		 // 모든 달은 28일까지 있다고 가정합니다.
		 // "2022.05.19"	["A 6", "B 12", "C 3"]	["2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"]	[1, 3]
		 
		 int[] ans2 = sol.solution("2020.01.01", 
				 new String[]{"Z 3", "D 5"},
				 new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"});	 // [1, 4, 5]
	}

}
