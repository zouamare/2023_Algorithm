import java.util.*;

public class Solution2 {
	
	// 투포인터의 좌표를 저장하고 두 좌표의 차이를 계산하기 위한 좌표 클래스 Point
	private static class Point implements Comparable<Point> {
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        // 범위가 작은 순서대로 gap을 계산하기 위해 클래스에 내부 메서드로 범위에 대한 계산 메서드를 추가함
        private int calcGap(int x, int y){
            return Math.abs(x-y);
        }
        
        // PriorityQueue 에서의 정렬 기준을 미리 선언함.
        @Override
        public int compareTo(Point p){
        	int gap = 0;
            if(this.x==p.x && this.y == p.y) gap = this.x-p.x ;
            else gap = (calcGap(this.x, this.y)) - calcGap(p.x, p.y);
            return gap;
        }
        
        // 디버깅용 toString
        @Override
        public String toString(){
            return "Point [ x = "+this.x+", y ="+this.y+" ]";
        }
    }
    
    public int[] solution(String[] gems) {
        
    	// 풀이 , Hmap과 hset을 사용한 투포인터로 풀이    	
        HashMap<String, Integer> hmap = new HashMap<>(); // 투포인터 배열 대신 hmap으로 사용함
        HashSet<String> hset = new HashSet<>();
        int N = gems.length; // 투 포인터의 범위 제한을 위한 입력 배열의 크기 상수 N
                                
        // 문제에서 요구하는 것은 어피치가 무지성으로 쇼핑한 결과 + gem이 최대 가짓수일 때의 최소 범위를 원하는 것이므로
        // 이러한 조건을 판단하기 위해 중복된 값을 자동으로 제거하는 HashSet에 입력값을 넣음.
        for(String x : gems){
            if(!hset.contains(x)) hset.add(x);
        }
        
        // 투포인터를 위한 좌표 변수 left와 right를 선언하고,
        int left = 0;
        int right = 0;
        // 주어진 조건을 만족하는 배열을의 범위 좌표 클래스(Point)를 저장하기 위한 pque
        // Point 클래스 자체에 우선순위가 설정되어 있으므로(좌표 범위가 짧은게 우선), 
        // 투 포인터기 때문에 똑같은 좌표를 넣을 일은 없지만, 같더라도 x좌표 오름차순으로 정렬
        PriorityQueue<Point> pque = new PriorityQueue<>();
        
        // 초기값을 넣어주고
        hmap.put(gems[left], 1);
        // 조건 점검 한번 해준다.
        judgeRange(hmap, hset, pque, left, left);            
 
 
        // 투포인터 돈 후
        while(true){
            judgeRange(hmap, hset, pque, left, right); // 범위 체크 + 조건 체크용 메서드!           
            
            if(left >= N-1 && right >= N-1) break; // 종료식
          
            // 투포인터 index 변화시켜주는 부분
            if(hmap.size() < hset.size() && right < N){
                right++;
                if(right < N){
                    plusGemMaps(gems[right], hmap);                    
                }
            }else {
                if(left < N) minusGemMaps(gems[left], hmap);
                left++;
            }
        }
                        
        // index 는 0부터 셈하기 때문에 +1 해줘서 문제에서 원하는 단위로 계산해주고
        int[] answer = {pque.peek().x+1, pque.peek().y+1};
        return answer; // 리턴
    }
    
    // 문제에서 주어진 최대 가짓수를 만족하는지 판단하는 로직 
    private static void judgeRange(HashMap<String, Integer> hmap, HashSet<String> hset, PriorityQueue<Point> pque, int left, int right){
          if(hmap.size()==hset.size()){ // 최대 가짓수인 경우에만 유효한 범위이므로
              pque.offer(new Point(left, right)); // pque에 넣어준다.
          }        
    }
    
    // right index가 증가할 때 사용될 메서드
    // 증가함에 따라 map에 값을 갱신해준다 (없으면 1, 있으면 +1)
    private static void plusGemMaps(String x, HashMap<String, Integer> hmap){
        if(hmap.containsKey(x)){
            hmap.replace(x, hmap.get(x)+1);
        }else {
            hmap.put(x, 1);
        }          
    }

    // left index가 증가할 때 사용될 메서드
    // 증가함에 따라 map에 값을 갱신해준다 (0보다 작거나 같으면 맵에서 완전 제거, 있으면 -1)
    private static void minusGemMaps(String x, HashMap<String, Integer> hmap){
        if(hmap.containsKey(x)){
            hmap.replace(x, hmap.get(x)-1);
            if(hmap.get(x) <= 0) hmap.remove(x);
        }          
    }

}


