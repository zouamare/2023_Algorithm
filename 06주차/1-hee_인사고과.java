package com.java.week5;


import java.util.*;


public class Solution2 {
	
	/*
	 * 
	 * 
	 * 이번에 제대로 배운거!!!
		자바에서 배열을 정렬할 때는 Arrays.sort()를 사용하고,
		ArrayList와 같은 컬렉션을 정렬할 때는 Collections.sort()를 사용한다.
		
		이 두 자료형에 객체를 넣는 경우 우선순위 비교룰 위해
				
		1. Comparable 인터페이스를 구현하거나
		2. xxx.sort() 메서드에 Comparator() 인터페이스를 구현해서
		
		정렬 기준을 제시할 수 있는데,		
		Arrays.sort()에서나 Collections.sort()에서나		
		들어갈 클래스에 Comparable를 구현하면, 
		당연히 두 메서드 모두에서 클래스에 Comparable을 기준으로 정렬하는 줄 알았는데,		
				
		Arrays.sort()는 Comparable을 기준으로
		Collections.sort()는 Comparator를 기준으로 정렬하게 되어있다.
		
		그래서 컬렉션으로 객체를 저정 및 정렬 할때는 Comparator()로 구현하는게 맞고, 
		int[] 같은 배열을 정렬할 때는 Comparable로 하는게 맞다.
		
		Comparable로 하니 몇가지 테스트 케이스에서
		내부적으로 다른 기준에 의해 정렬이 되어서 이상한 값을 출력했던 모양...
	 * 
	 */
	
	// 
		
	static class Person implements Comparable<Person>{
		
		int workPoint, coPoint;

		public Person(int workPoint, int coPoint) {
			this.workPoint = workPoint;
			this.coPoint = coPoint;
		}

		@Override
		public String toString() {
			return "Person [workPoint=" + workPoint + ", coPoint=" + coPoint + "]";
		}

		@Override
		public int compareTo(Person o) {
			
			if(this.workPoint < o.workPoint) {
				return 1;
			}else if(this.workPoint == o.workPoint) {
				if(this.coPoint > o.workPoint) {
					return 1;
				}else {
					return -1;
				}				
			}
			else return -1;
			
		}
	}
	
	
	static ArrayList<Person> arrs;
	
	/* 잘못 푼 풀이법! */
	/*
    public static int solution(int[][] scores) {
    	
    	int answer = 1;
    	Person wanho = new Person(scores[0][0], scores[0][1]);
    	arrs = new ArrayList<>();
    	
    	for(int[] score : scores) {
    		Person p = new Person(score[0], score[1]);
    		arrs.add(p);    		
    	}
    	
    	Collections.sort(arrs);
    	
    	int wanhoPoint = wanho.coPoint + wanho.workPoint;
    	int peerPoint = 0;
    	
    	for(Person p : arrs) {
    		if(p.coPoint < peerPoint) {    			
    			if(p.coPoint == wanho.coPoint
    					&& p.workPoint == wanho.workPoint) {
    				return -1;
    			}
    		}else {
    			peerPoint = Math.max(p.coPoint, peerPoint);
    			if(wanhoPoint < p.workPoint+p.coPoint) {
    				answer++;
    			}
    		}
    		
    	}    	    
        return answer;
    }
    */
	
	 public static int solution(int[][] scores) {
	    	
	    	int answer = 1;
	    	Person wanho = new Person(scores[0][0], scores[0][1]);
	    	arrs = new ArrayList<>();
	    	
	    	for(int[] score : scores) {
	    		Person p = new Person(score[0], score[1]);
	    		arrs.add(p);    		
	    	}
	    	
	    	Collections.sort(arrs, new Comparator<Person>() {

				@Override
				public int compare(Person o1, Person o2) {
					if(o1.workPoint < o2.workPoint) {
						return 1;
						
					}else if(o1.workPoint == o2.workPoint) {
						if(o1.coPoint > o2.coPoint) {
							return 1;
						}else return -1;					
					}
					else return -1;
				}
			});
	    	
	    	
	    	int wanhoPoint = wanho.coPoint + wanho.workPoint;
	    	int peerPoint = 0;
	    	
	    	for(Person p : arrs) {
	    		
	    		if(p.coPoint < peerPoint) {    			
	    			if(p.coPoint == wanho.coPoint
	    					&& p.workPoint == wanho.workPoint) {
	    				return -1;
	    			}
	    		}else {
	    			peerPoint = Math.max(p.coPoint, peerPoint);
	    			if(wanhoPoint < p.workPoint+p.coPoint) {
	    				answer++;
	    			}
	    		}    		
	    	}    	    
	        return answer;
	    }
    	
	public static void main(String[] args) {
				
		int ans1 = solution(new int[][] {
			{2,2}, {1,4}, {3,2}, {3,2}, {2,1}
		});
		
//		int ans2 = solution(new int[][] {
//			{2, 2}, {2, 2}, {2, 3}, {3, 2}
//		});
		
		System.out.println(ans1);
//		System.out.println(ans2);
		
	}

}
