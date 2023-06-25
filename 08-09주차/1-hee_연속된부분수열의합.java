package com.java.week8;
import java.util.*;

public class Solution1 {
	
    private static class Point implements Comparable<Point>{
        int left, right;
        Point(int left, int right){
            this.left = left;
            this.right = right;
        }
        
        @Override
        public String toString(){
            return "Point ["+this.left+", "+this.right+"]";
        }
        
     
        @Override
        public int compareTo(Point o){
            return this.left - o.left;
        }
        
    }
    
    public int[] solution(int[] sequence, int k) {
        
        int left = 0;
        int right = 0;
        int N = sequence.length;        
        int sum = sequence[left]; 
        
        ArrayList<Point> points = new ArrayList<>();
        
        // 배열의 끝까지 반복문을 돌며 포인터 두개를 이동합니다.
        while(true){           	
            if(sum==k){ // 문제에서 원하는 부분합인지 체크
                if(left < N && right < N){ // 두 포인터 모두 유효한 범위 이내라면,
                    points.add(new Point(left, right)); // 두 포인터의 좌표를 저장합니다.
                }
            }
            
            if(left == N && right == N) break; // 두 포인터 모두 끝까지 이동했다면, 반복을 종료합니다.
            
            if(sum <= k && right < N){ // 부분합보다 작거나 같다 -> 포인터의 이동이 필요하다 + right 포인터의 범위 제한을 위한 조건식
                right++; // 포인터를 먼저 이동해주고
                if(right < N ) sum += sequence[right]; // 부분합을 더하는데, 만약 포인터가 배열의 범위를 벗어난다면 연산을 하지 않아야 한다.
            }else {
                if(left < N) sum -= sequence[left]; // 부분합을 빼는데, 먼저 값을 빼주어야 한다.
                left++; // 포인터를 이동
            }                        
        }
                
        int diff = Integer.MAX_VALUE; // 최소 값 비교를 위한 초기값 설정
        int from = -1; // 그냥 설정
        int to = -1;
        
        // 좌표를 담은 point 객체의 우선순위를 
        // left 좌표의 오름차 순으로 설정했기 때문에 left 좌표의 오름차 순으로 정렬된다.
        Collections.sort(points); 
        
        for(Point p : points){ // ArrayList의 크기만큼 순회하면서
            if(diff > p.right - p.left){ // 두 좌표의 차이가 최소치를 갱신한다면(>=로 비교하지 않으므로 같은 경우는 무시된다)
                diff = p.right - p.left; // 최소차를 갱신하고
                from = p.left; // 좌표를 저장한다.
                to = p.right; 
            }
        }
                
        int[] answer = {from, to}; // 반복문을 돈 후 구해진 최소 좌표들의 배열을 리턴
        return answer;
    }

}
