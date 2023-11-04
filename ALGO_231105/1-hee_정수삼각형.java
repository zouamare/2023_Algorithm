class Solution {
    public int solution(int[][] triangle) {
        
        // https://codedrive.tistory.com/49        
        
        int answer = 0;
        for(int i = 1 ; i < triangle.length; i ++){
            for(int j = 0 ; j < i+1; j++){
                if(j==0){ // 왼쪽 사이드
                    triangle[i][j] += triangle[i-1][j];
                }else if(j==i){ // 오른쪽 사이드
                    triangle[i][j] += triangle[i-1][j-1];
                }else {
                    // 현재 지점의 최대 값은 좌측 위의 값과, 우측 위의 값을 비교하여 최대인 것으로 갱신한다.
                    triangle[i][j] += Math.max(triangle[i-1][j], triangle[i-1][j-1]) ;
                }
                answer = Math.max(answer, triangle[i][j]); // 최대값 갱신
            }
        }
                
        return answer;
    }
}