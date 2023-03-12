package com.java.week2;

public class Answer2_4단고음_조원희 {
	
	// 참고 링크 : 
	// https://train-validation-test.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-Level-4-4%EB%8B%A8-%EA%B3%A0%EC%9D%8C-Java
	
	 public static int solution(int n) {
	        return getCaseNumber(n-2, 2);
	 }
	 
	 
	 private static int getCaseNumber(int n, int p) {		 
		 if(n==3 && p==2) return 1;
		 else if(n < 3 || Math.log(n)/Math.log(3)*2 < p) return 0;
		 else if(n==3 && p ==3) return 0;		 
		 
		 return 
				 getCaseNumber(n-1, p+1)
				 +(n%3==0 && p > 1 ? 
				 getCaseNumber(n/3, p-2) : 0);
	 }
	 
	public static void main(String[] args) {
		
		
		int n = solution(15);
		System.out.println(n);

		n = solution(24);
		System.out.println(n);

		n = solution(41);
		System.out.println(n);

		n = solution(Integer.MAX_VALUE);
		System.out.println(n);

	}

}
