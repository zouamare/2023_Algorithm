import java.util.*;

class Solution {
        
    private static class Key { 
        
        Queue<int[]> keyPoints; // 키가 위치한 곳의 좌표를 담을 큐
     
        public Key(){
            this.keyPoints = new LinkedList<int[]>();
        }
        
        void rotate(){ // 90도 회전 메서드                      
            int cnt = 0; 
            int size = this.keyPoints.size();
            
            while(cnt < size){
                int[] pos = this.keyPoints.poll();
                int x = pos[0];
                int y = pos[1];
                int[] rotatedPosition = new int[] { N - 1 - y, x }; // 90도 회전 일반식
                this.keyPoints.add(rotatedPosition);
                cnt++;
            }            
        }
        
        // key 의 배열을 그려서 가져오는 메서드
        // 범위체크!
        // lock 배열인 N을 기준으로 그려온다.
        int[][] getKeyMap(int rowCoef, int colCoef){  // 보정계수를 파라미터로 받음
            int[][] map = new int[N][N];
            
            // lock의 범위에 넘어가지 않는 선에서 key의 배열을 그림.
            for(int[] keyPos : keyPoints){
                int x = keyPos[0] + rowCoef;
                int y = keyPos[1] + colCoef;
                if (x < 0 || y < 0 || x >= N || y >= N) continue;
                map[x][y] = 1;
            }
            
            return map;            
        }
        
        // crossCheck
        boolean crossCheck(){ 
            // coefficient
            int rowCoef = -N; // row 좌표의 보정계수 (- N ~ N)
            int colCoef = -N; // col 좌표의 보정 계수 (- N ~ N)
            boolean isFit = true;
            
            J:for(int rcf = -N ; rcf < N ; rcf++){
                for(int ccf = -N ; ccf < N ; ccf++){                    
                    //printMap(rcf, ccf);
                    int[][] keyMap = getKeyMap(rcf, ccf);

                    L:for(int r = 0 ; r < N ; r++){
                        for(int c = 0; c < N ; c++){
                            if(keyMap[r][c] != lockMap[r][c]) {
                                isFit = false;
                                break L;
                            } else {
                                isFit = true;
                            }
                        }
                    }
                    if(isFit) return isFit;                                
                    
                }
            }
            return isFit;
        }                                
    }
    
    // 배열의 크기 N은 M <= N 이 성립한다.
    private static int M; // key의 범위
    private static int N; // lock의 범위
    private static int[][] lockMap;
    public boolean solution(int[][] key, int[][] lock) {
        
        M = key.length;
        
        Key keyObj = new Key(); // key에 대한 정보를 저장하고 값을 관리하기 위한 객체 생성함
        // 주어진 입력값에 대하여 key의 돌기 부분의 좌표를 저장.
        for(int r = 0; r < M ; r++){
            for(int c = 0; c < M ; c++){
                if( key[r][c] > 0 ) {
                    keyObj.keyPoints.add(new int[]{r,c});
                } 
            }
        }
        
        N = lock.length;
        lockMap = new int[N][N]; // lock 배열은 회전, 이동 등의 작업이 일어나지 않으므로 전역변수로 관리함.        
        // 주어진 입력에서는 lock 홈 부분을 0으로 표현했는데, 나중에 key와 == 연산을 위해 값을 반전시켜준다.
        for(int r = 0 ; r < N ; r++){
            for(int c = 0 ; c < N ; c++){
                lockMap[r][c] = 1 - lock[r][c]; // 입력값의 홈 부분을 key에 대응하기 위해 값을 반전시켜줌.
            }
        }        
        
        boolean answer = false;
        
        for(int d = 0 ; d < 4 ; d++){ // 4번만 돌면 모든 경우의 수 탐색!
            answer = keyObj.crossCheck();
            keyObj.rotate();            
            if(answer) break;
        }
                
        return answer;
    }
    
    
    
}